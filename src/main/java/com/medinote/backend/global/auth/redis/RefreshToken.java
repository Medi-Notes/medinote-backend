package com.medinote.backend.global.auth.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Getter
@RedisHash("refreshToken")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
  @Id
  private Long memberId;

  private String refreshToken;

  @TimeToLive(unit = TimeUnit.MINUTES)
  private Integer expiration;
}