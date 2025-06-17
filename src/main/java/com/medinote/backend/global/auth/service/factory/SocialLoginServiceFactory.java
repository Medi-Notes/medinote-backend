package com.medinote.backend.global.auth.service.factory;


import com.medinote.backend.domain.member.entity.SocialPlatform;
import com.medinote.backend.global.auth.service.SocialLoginService;
import com.medinote.backend.global.auth.service.strategy.GoogleLoginService;
import com.medinote.backend.global.auth.service.strategy.KakaoLoginService;
import com.medinote.backend.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.medinote.backend.global.exception.enums.ErrorType.INVALID_SOCIAL_PLATFORM_ERROR;


@Component
@RequiredArgsConstructor
public class SocialLoginServiceFactory {
    private final GoogleLoginService googleLoginService;
    private final KakaoLoginService kakaoLoginService;


    public SocialLoginService getService(SocialPlatform socialPlatform) {
        return switch (socialPlatform) {
            case GOOGLE -> googleLoginService;
            case KAKAO -> kakaoLoginService;
            default -> throw new CustomException(INVALID_SOCIAL_PLATFORM_ERROR);
        };
    }
}
