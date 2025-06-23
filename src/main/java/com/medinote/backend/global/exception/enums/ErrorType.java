package com.medinote.backend.global.exception.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public enum ErrorType {
    /**
     * 400 BAD REQUEST
     */

    // 표준 오류
    REQUEST_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INVALID_TYPE_ERROR(HttpStatus.BAD_REQUEST, "잘못된 타입이 입력되었습니다."),
    INVALID_MISSING_HEADER_ERROR(HttpStatus.BAD_REQUEST, "요청에 필요한 헤더값이 존재하지 않습니다."),
    INVALID_HTTP_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "요청 형식이 허용된 형식과 다릅니다."),
    INVALID_HTTP_METHOD_ERROR(HttpStatus.BAD_REQUEST, "지원되지 않는 HTTP method 요청입니다."),
    INVALID_TOKEN_HEADER_ERROR(HttpStatus.BAD_REQUEST, "토큰 헤더값의 형식이 잘못되었습니다."),
    INVALID_CODE_ERROR(HttpStatus.BAD_REQUEST, "code 값의 형식이 잘못되었습니다."),
    INVALID_SOCIAL_PLATFORM_ERROR(HttpStatus.BAD_REQUEST, "잘못된 소셜 플랫폼 이름입니다."),
    JSON_PARSING_ERROR(HttpStatus.BAD_REQUEST, "JSON 파싱에 실패했습니다."),

    // 인증 관련 오류
    EMPTY_PRINCIPLE_ERROR(HttpStatus.BAD_REQUEST, "Principle 객체가 없습니다. (null)"),

    /**
     * 401 UNAUTHORIZED
     */
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않는 JWT 토큰입니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 JWT 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "지원하지 않는 JWT 토큰입니다."),
    EMPTY_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "JWT 토큰이 존재하지 않습니다."),
    INVALID_JWT_SIGNATURE(HttpStatus.UNAUTHORIZED, "잘못된 JWT 서명입니다."),
    UNKNOWN_JWT_ERROR(HttpStatus.UNAUTHORIZED, "알 수 없는 JWT 토큰 오류가 발생했습니다."),

    INVALID_SOCIAL_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 소셜 엑세스 토큰입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 리프레시 토큰입니다, 다시 로그인을 해주세요."),

    /**
     * 404 NOT FOUND
     */
    NOT_FOUND_MEMBER_ERROR(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    NOT_FOUND_REFRESH_TOKEN_ERROR(HttpStatus.NOT_FOUND, "존재하지 않는 리프레시 토큰입니다."),
    NOT_FOUND_FAVORITE_ERROR(HttpStatus.NOT_FOUND, "존재하지 않는 즐겨찾기입니다."),
    NOT_FOUND_NOTICE_ERROR(HttpStatus.NOT_FOUND,"존재하지 않는 임대주택 공고입니다."),

    /**
     * 409 CONFLICT
     */
    NICKNAME_DUP_ERROR(HttpStatus.CONFLICT, "중복된 회원 닉네임입니다."),

    // 공공임대주택 관련 오류

    /**
     * 404 NOT FOUND
     */
    HOUSE_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 HOUSE ID 입니다."),

    MEMBER_ALREADY_EXIST_ERROR(HttpStatus.CONFLICT, "이미 존재하는 사용자입니다."),

    /**
     * 500 INTERNAL_SERVER_ERROR
     */
    S3_UPLOAD_FILE(HttpStatus.INTERNAL_SERVER_ERROR, "S3 업로드에 실패했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
