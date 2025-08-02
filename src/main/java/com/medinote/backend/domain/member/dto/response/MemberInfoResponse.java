package com.medinote.backend.domain.member.dto.response;

import com.medinote.backend.domain.member.entity.Member;
import com.medinote.backend.domain.member.entity.SocialPlatform;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MemberInfoResponse(
        String name,
        String email,
        SocialPlatform socialPlatform,
        LocalDateTime createAt
) {
    public static MemberInfoResponse of(String name, String email, SocialPlatform socialPlatform, LocalDateTime createAt) {
        return new MemberInfoResponse(name, email, socialPlatform, createAt);
    }

    public static MemberInfoResponse from(Member member) {
        return MemberInfoResponse.of(
                member.getName(),
                member.getEmail(),
                member.getSocialPlatform(),
                member.getCreatedAt()
        );
    }
}
