package com.medinote.backend.domain.member.controller;

import com.medinote.backend.domain.member.dto.request.GetAccessTokenRequest;
import com.medinote.backend.domain.member.dto.request.LoginRequest;
import com.medinote.backend.domain.member.dto.request.ReissueRequest;
import com.medinote.backend.domain.member.dto.response.MemberInfoResponse;
import com.medinote.backend.domain.member.dto.response.TokenResponse;
import com.medinote.backend.domain.member.service.MemberService;
import com.medinote.backend.global.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.medinote.backend.global.exception.enums.SuccessType.*;


@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController implements MemberApi {
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(ApiResponse.success(
                LOGIN_SUCCESS,
                memberService.login(loginRequest)
        ));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<MemberInfoResponse>> me(Principal principal) {

        return ResponseEntity.ok(ApiResponse.success(
                GET_ME_SUCCESS,
                memberService.me(principal)
        ));
    }

    @PostMapping("/token")
    public ResponseEntity<ApiResponse<String>> getAccessToken(@Valid @RequestBody GetAccessTokenRequest getAccessTokenRequest) {

        return ResponseEntity.ok(memberService.getSocialAccessToken(getAccessTokenRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(Principal principal) {

        memberService.logout(principal);
        return ResponseEntity.ok(ApiResponse.success(LOGOUT_SUCCESS));
    }

    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<?>> withdraw(Principal principal) {

        memberService.withdraw(principal);
        return ResponseEntity.ok(ApiResponse.success(WITHDRAW_MEMBER_SUCCESS));
    }

    @PostMapping("/reissue")
    public ResponseEntity<ApiResponse<TokenResponse>> reissueToken(@Valid @RequestBody ReissueRequest reissueRequest) {

        return ResponseEntity.ok(ApiResponse.success(
                REISSUE_SUCCESS,
                memberService.reissueToken(reissueRequest)
        ));
    }

}
