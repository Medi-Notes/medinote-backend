package com.medinote.backend.global.auth.feign.google;

import com.medinote.backend.global.auth.feign.google.dto.response.GoogleUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "GoogleApiClient", url = "https://www.googleapis.com")
public interface GoogleApiClient {
    @GetMapping("/userinfo/v2/me")
    GoogleUserResponse getUserInformation(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);
}
