package com.medinote.backend.domain.medinote.service;

import com.medinote.backend.domain.medinote.dto.request.*;
import com.medinote.backend.domain.medinote.dto.response.MedinoteListResponse;
import com.medinote.backend.domain.medinote.dto.response.MedinoteResponse;
import com.medinote.backend.domain.medinote.entity.Medinote;
import com.medinote.backend.domain.medinote.entity.MedinoteState;
import com.medinote.backend.domain.medinote.repository.MedinoteRepository;
import com.medinote.backend.domain.member.entity.Member;
import com.medinote.backend.domain.member.repository.MemberRepository;
import com.medinote.backend.global.auth.jwt.JwtProvider;
import com.medinote.backend.global.aws.service.S3Service;
import com.medinote.backend.global.aws.service.SqsService;
import com.medinote.backend.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.Principal;

import static com.medinote.backend.global.exception.enums.ErrorType.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MedinoteService {
    private final S3Service s3Service;
    private final SqsService sqsService;
    private final MedinoteRepository medinoteRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MedinoteResponse createMedinote(MultipartFile audioFile, Principal principal) {
        Long memberId = JwtProvider.getMemberIdFromPrincipal(principal);
        Member member = memberRepository.getMemberByMemberId(memberId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_MEMBER_ERROR));

        String s3Key = s3Service.generateS3Key(memberId, audioFile.getOriginalFilename());
        try {
            s3Service.upload(audioFile, s3Key);
        } catch (IOException e) {
            throw new CustomException(S3_UPLOAD_FILE);
        }

        Medinote medinote = Medinote.builder()
                .medinoteTitle(audioFile.getOriginalFilename())
                .s3Key(s3Key)
                .memberId(member)
                .medinoteState(MedinoteState.PENDING)
                .build();

        medinoteRepository.save(medinote);

        return MedinoteResponse.from(medinote);
    }

    @Transactional
    public Integer updateMedinoteState(Long medinoteId, UpdateMedinoteStateRequest request) {
        return medinoteRepository.updateMedinoteStateById(medinoteId, request.medinoteState());
    }

    @Transactional
    public Integer updateSttText(Long medinoteId, UpdateSttTextRequest request) {
        return medinoteRepository.updateSttTextById(medinoteId, request.sttText());
    }

    @Transactional
    public Integer updateMedinoteText(Long medinoteId, UpdateMedinoteTextRequest request) {
        return medinoteRepository.updateMedinoteTextById(medinoteId, request.medinoteText());
    }

    public MedinoteListResponse getMedinoteList(Principal principal) {
        Long memberId = JwtProvider.getMemberIdFromPrincipal(principal);
        Member member = memberRepository.getMemberByMemberId(memberId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_MEMBER_ERROR));

        return MedinoteListResponse.from(medinoteRepository.findAllByMemberId(member));
    }

    @Transactional
    public void deleteMedinotes(DeleteMedinotesRequest request) {
        for (Long medinoteId : request.medinoteIdList()) {
            deleteMedinote(medinoteId);
        }
    }

    @Transactional
    public void deleteMedinote(Long medinoteId) {
        medinoteRepository.deleteById(medinoteId);
    }

    public void sendMedinoteTransformMessage(Long medinoteId) {
        Medinote medinote = medinoteRepository.findById(medinoteId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_MEDINOTE_ERROR));

        // 여기서도 상태 검사하는 로직 있으면 좋을듯? 나중에 추가하자

        sqsService.sendMedinoteMessage(medinoteId, medinote.getS3Key());
    }
}
