package com.medinote.backend.global.auth.feign.kakao;

import com.medinote.backend.global.auth.feign.kakao.dto.response.KakaoAccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "KakaoAuthApiClient", url = "https://kauth.kakao.com")
public interface KakaoAuthApiClient {
    //Authorization Code 방식으로 Access Token, Refresh Token 발급
    // 근데 실질적으로는 Access Token만 사용
    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KakaoAccessTokenResponse getOAuth2AccessToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("code") String code
    );
}
