package com.medinote.backend.domain.member.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SocialPlatform {
    KAKAO("카카오"),
    GOOGLE("구글"),
    NAVER("네이버");

    private final String value;
}