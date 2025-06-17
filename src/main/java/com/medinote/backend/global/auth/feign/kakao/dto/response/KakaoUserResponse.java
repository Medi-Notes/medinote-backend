package com.medinote.backend.global.auth.feign.kakao.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
// 카카오 응답은 camel case가 아니라 snake case이기 때문에 응답 매핑을 위해 사용
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoUserResponse {
    private Long id;
    private KakaoAccount kakaoAccount;
}
