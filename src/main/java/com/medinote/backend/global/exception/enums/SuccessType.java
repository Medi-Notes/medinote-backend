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
  
    // 공공임대주택 관련
    GET_RENTALHOUSES_SUCCESS(HttpStatus.OK, "공공임대주택 리스트 조회에 성공했습니다."),
    GET_RENTALHOUSES_BY_DONGCODE_SUCCESS(HttpStatus.OK, "동별 공공임대주택 리스트 조회에 성공했습니다."),
    GET_RENTALHOUSE_SUCCESS(HttpStatus.OK, "공공임대주택 조회에 성공했습니다."),
    GET_RENTALHOUSES_WITHIN_SUCCESS(HttpStatus.OK, "지도 내 공공임대주택 리스트 조회에 성공했습니다."),
    GET_GUGUN_SUMMARY_SUCCESS(HttpStatus.OK, "구군별 공공임대주택 summary 조회에 성공했습니다."),
    GET_DONG_SUMMARY_SUCCESS(HttpStatus.OK, "동별 공공임대주택 summary 조회에 성공했습니다."),

    // 공공임대주택 공고 관련
    GET_RENTALNOTICE_LIST_SUCCESS(HttpStatus.OK, "공공임대주택 공고 리스트 조회에 성공했습니다."),
    GET_RENTALNOTICE_SUCCESS(HttpStatus.OK, "공공임대주택 공고 단일 조회에 성공했습니다."),
    GET_LIKED_NOTICE_SUCCESS(HttpStatus.OK, "관심 임대주택 공고 조회에 성공했습니다."),

    /**
     * 201 CREATED
     */
    MEMBER_CREATED(HttpStatus.CREATED, "회원가입에 성공했습니다."),
    ADD_LIKED_NOTICE_CREATED(HttpStatus.CREATED, "관심 공고가 추가되었습니다."),

    /**
     * 204 NO CONTENT
     */
    DELETE_LIKED_NOTICE_SUCCESS(HttpStatus.NO_CONTENT, "관심 공고가 더이상 존재하지 않습니다.");


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}