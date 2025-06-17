package com.medinote.backend.domain.member.controller;

import com.medinote.backend.domain.member.dto.request.GetAccessTokenRequest;
import com.medinote.backend.domain.member.dto.request.LoginRequest;
import com.medinote.backend.domain.member.dto.request.ReissueRequest;
import com.medinote.backend.domain.member.dto.response.MemberInfoResponse;
import com.medinote.backend.domain.member.dto.response.TokenResponse;
import com.medinote.backend.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

@Tag(name = "멤버 컨트롤러", description = "멤버 관련 API입니다.")
public interface MemberApi {
    @Operation(summary = "로그인", description = "로그인 API 입니다.", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "로그인 완료")
    })
    ResponseEntity<ApiResponse<TokenResponse>> login(LoginRequest loginRequest);

    @Operation(summary = "로그아웃", description = "로그아웃 API 입니다.", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "로그아웃 완료")
    })
    ResponseEntity<ApiResponse<?>> logout(Principal principal);

    @Operation(summary = "me 조회", description = "me 정보를 조회하는 API 입니다.", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "me 조회 완료")
    })
    ResponseEntity<ApiResponse<MemberInfoResponse>> me(Principal principal);

    @Operation(summary = "회원탈퇴", description = "회원탈퇴 API 입니다.", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "회원탈퇴 완료")
    })
    ResponseEntity<ApiResponse<?>> withdraw(Principal principal);

    @Operation(summary = "access token 조회", description = "access token 조회하는 API 입니다.", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "access token 조회 완료")
    })
    ResponseEntity<ApiResponse<String>> getAccessToken(GetAccessTokenRequest getAccessTokenRequest);

    @Operation(summary = "token 갱신", description = "token 갱신하는 API 입니다.", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "token 갱신 완료")
    })
    ResponseEntity<ApiResponse<TokenResponse>> reissueToken(ReissueRequest reissueRequest);
}
