package com.medinote.backend.domain.member.dto.response;

import com.medinote.backend.global.auth.jwt.TokenDTO;

public record TokenResponse(MemberInfoResponse member, TokenDTO token) {
    public static TokenResponse of(MemberInfoResponse member, TokenDTO token) {
        return new TokenResponse(member, token);
    }
}
