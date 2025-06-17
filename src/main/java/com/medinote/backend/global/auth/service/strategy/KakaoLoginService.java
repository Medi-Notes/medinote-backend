package com.medinote.backend.global.auth.service.strategy;


import com.medinote.backend.domain.member.entity.Member;
import com.medinote.backend.global.auth.feign.kakao.KakaoApiClient;
import com.medinote.backend.global.auth.feign.kakao.KakaoAuthApiClient;
import com.medinote.backend.global.auth.feign.kakao.dto.response.KakaoAccessTokenResponse;
import com.medinote.backend.global.auth.feign.kakao.dto.response.KakaoUserResponse;
import com.medinote.backend.global.auth.service.SocialLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoLoginService implements SocialLoginService {
    @Value("${oauth.kakao.client-id}")
    private String CLIENT_ID;
    @Value("${oauth.kakao.authorization-grant-type}")
    private String GRANT_TYPE;
    @Value("${oauth.kakao.redirect-uri}")
    private String REDIRECT_URL;

    private final KakaoAuthApiClient kakaoAuthApiClient;
    private final KakaoApiClient kakaoApiClient;

    public String getSocialAccessToken(String code) {

        // Authorization code로 카카오 Access Token 불러오기
        KakaoAccessTokenResponse tokenResponse = kakaoAuthApiClient.getOAuth2AccessToken(
                GRANT_TYPE,
                CLIENT_ID,
                REDIRECT_URL,
                code
        );

        // Refresh Token은 사용 안함
        return tokenResponse.getAccessToken();
    }

    public String getSocialId(String socialAccessToken) {
        KakaoUserResponse userResponse = kakaoApiClient.getUserInformation("Bearer " + socialAccessToken);

        return Long.toString(userResponse.getId());
    }

    public void setSocialInfo(Member loginMember, String socialAccessToken) {
        // Access Token으로 유저 정보 불러와서 설정
        KakaoUserResponse kakaoUser = kakaoApiClient.getUserInformation("Bearer " + socialAccessToken);

        loginMember.setMemberInfo(
                kakaoUser.getKakaoAccount().getProfile().getNickname(),
                ""
        );
    }
}
