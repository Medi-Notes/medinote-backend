package com.medinote.backend.global.auth.feign.kakao;

import com.medinote.backend.global.auth.feign.kakao.dto.response.KakaoUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="KakoApiClient", url="https://kapi.kakao.com")
public interface KakaoApiClient {
    // AccessToken을 이용하여 유저 정보 가져오기
    @GetMapping("/v2/user/me")
    KakaoUserResponse getUserInformation(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);
}
