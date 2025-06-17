package com.medinote.backend.domain.member.dto.response;

import com.medinote.backend.domain.member.entity.Member;
import com.medinote.backend.domain.member.entity.SocialPlatform;

public record MemberInfoResponse(
        String name,
        String email,
        SocialPlatform socialPlatform
) {
    public static MemberInfoResponse of(String name, String email, SocialPlatform socialPlatform) {
        return new MemberInfoResponse(name, email, socialPlatform);
    }

    public static MemberInfoResponse from(Member member) {
        return MemberInfoResponse.of(
                member.getName(),
                member.getEmail(),
                member.getSocialPlatform()
        );
    }
}
