package com.medinote.backend.global.auth.feign.google.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleAccessTokenResponse {
    private String accessToken;
    private String refreshToken;

    public static GoogleAccessTokenResponse of(String accessToken, String refreshToken) {
        return new GoogleAccessTokenResponse(accessToken, refreshToken);
    }
}