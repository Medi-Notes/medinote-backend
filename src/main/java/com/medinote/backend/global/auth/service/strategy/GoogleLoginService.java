package com.medinote.backend.global.auth.service.strategy;


import com.medinote.backend.domain.member.entity.Member;
import com.medinote.backend.global.auth.feign.google.GoogleApiClient;
import com.medinote.backend.global.auth.feign.google.GoogleAuthApiClient;
import com.medinote.backend.global.auth.feign.google.dto.response.GoogleAccessTokenResponse;
import com.medinote.backend.global.auth.feign.google.dto.response.GoogleUserResponse;
import com.medinote.backend.global.auth.service.SocialLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Transactional
public class GoogleLoginService implements SocialLoginService {
    @Value("${oauth.google.client-id}")
    private String CLIENT_ID;
    @Value("${oauth.google.client-secret}")
    private String CLIENT_SECRET;
    @Value("${oauth.google.authorization-grant-type}")
    private String GRANT_TYPE;
    @Value("${oauth.google.redirect-uri}")
    private String REDIRECT_URL;

    private final GoogleAuthApiClient googleAuthApiClient;
    private final GoogleApiClient googleApiClient;

    public String getSocialAccessToken(String code) {
        GoogleAccessTokenResponse tokenResponse = googleAuthApiClient.getOAuth2AccessToken(
                GRANT_TYPE,
                CLIENT_ID,
                CLIENT_SECRET,
                REDIRECT_URL,
                code,
                ""
        );

        return tokenResponse.getAccessToken();
    }

    public String getSocialId(String socialAccessToken) {
        GoogleUserResponse googleUser = googleApiClient.getUserInformation("Bearer " + socialAccessToken);

        return googleUser.getId();
    }

    public void setSocialInfo(Member loginMember, String socialAccessToken) {
        GoogleUserResponse googleUser = googleApiClient.getUserInformation("Bearer " + socialAccessToken);
        String username = googleUser.getName();

        if (isNull(username)) {
            username = "Google_User_" + UUID.randomUUID().toString().substring(0, 6);
        }

        loginMember.setMemberInfo(username, googleUser.getEmail());
    }
}
