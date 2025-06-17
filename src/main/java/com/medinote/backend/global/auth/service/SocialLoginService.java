package com.medinote.backend.global.auth.service;


import com.medinote.backend.domain.member.entity.Member;

public interface SocialLoginService {
    String getSocialAccessToken(String code);

    String getSocialId(String socialAccessToken);

    void setSocialInfo(Member loginMember, String socialAccessToken);
}
