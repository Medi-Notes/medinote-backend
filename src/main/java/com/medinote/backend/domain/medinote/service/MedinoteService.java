package com.medinote.backend.domain.medinote.service;

import com.medinote.backend.domain.medinote.entity.Medinote;
import com.medinote.backend.domain.medinote.entity.MedinoteState;
import com.medinote.backend.domain.medinote.repository.MedinoteRepository;
import com.medinote.backend.domain.member.entity.Member;
import com.medinote.backend.domain.member.repository.MemberRepository;
import com.medinote.backend.global.auth.jwt.JwtProvider;
import com.medinote.backend.global.aws.service.S3Service;
import com.medinote.backend.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

import static com.medinote.backend.global.exception.enums.ErrorType.NOT_FOUND_MEMBER_ERROR;
import static com.medinote.backend.global.exception.enums.ErrorType.S3_UPLOAD_FILE;

@Service
@RequiredArgsConstructor
public class MedinoteService {
    private final S3Service s3Service;
    private final MedinoteRepository medinoteRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String createMedinote(MultipartFile audioFile, Principal principal) {
        Long memberId = JwtProvider.getMemberIdFromPrincipal(principal);
        Member member = memberRepository.getMemberByMemberId(memberId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_MEMBER_ERROR));;

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

        return s3Key;
    }
}
