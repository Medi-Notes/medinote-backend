package com.medinote.backend.global.exception.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessType {

    /**
     * 200 OK
     */
  
    // 회원가입/로그인
    PROCESS_SUCCESS(HttpStatus.OK, "OK"),

    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
    REISSUE_SUCCESS(HttpStatus.OK, "Access 토큰 재발급에 성공했습니다."),
    LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃에 성공했습니다."),
    SOCIAL_ACCESS_TOKEN_SUCCESS(HttpStatus.OK, "OAuth2 엑세스 토큰을 가져오는데 성공했습니다."),
    SET_MEMBER_NICKNAME_SUCCESS(HttpStatus.OK, "유저 닉네임을 설정하여 회원가입에 성공했습니다."),
    WITHDRAW_MEMBER_SUCCESS(HttpStatus.OK, "회원 탈퇴가 완료되었습니다."),

    GET_ME_SUCCESS(HttpStatus.OK, "회원 조회에 성공했습니다."),
  
    // 메디노트 관련
    CREATE_MEDINOTE_SUCCESS(HttpStatus.CREATED, "메디노트 생성에 성공했습니다."),
    UPDATE_MEDINOTE_STATE_SUCCESS(HttpStatus.OK, "메디노트 상태가 업데이트 되었습니다."),
    UPDATE_STT_TEXT_SUCCESS(HttpStatus.OK, "sttText가 업데이트 되었습니다."),
    UPDATE_MEDINOTE_TEXT_SUCCESS(HttpStatus.OK, "메디노트 Text가 업데이트 되었습니다."),
    GET_MEDINOTE_LIST_SUCCESS(HttpStatus.OK, "메디노트 목록 조회에 성공했습니다."),


    /**
     * 201 CREATED
     */
    MEMBER_CREATED(HttpStatus.CREATED, "회원가입에 성공했습니다.");



    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}