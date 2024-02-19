package com.kjw.sharemore.apiPayLoad.code.status;

import com.kjw.sharemore.apiPayLoad.code.BaseErrorCode;
import com.kjw.sharemore.apiPayLoad.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 내부 에러"),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다"),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다"),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다"),
    //가장 일반저긴 응답

    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP40001", "이거는 테스트"),

    //user
    EXSIST_USER(HttpStatus.CONFLICT, "USER001", "이미 존재하는 유저입니다"),
    NO_EXIST_USER(HttpStatus.NOT_FOUND, "USER002", "존재하지 않는 유저입니다"),

    //reservation
    DATE_TIME_ERROR(HttpStatus.BAD_REQUEST, "RESERVATION002", "시작 시간보다 이전 시간을 선택할 수 없습니다"),
    DUPLICATE_RESERVATION(HttpStatus.CONFLICT, "RESERVATION003", "이미 예약된 시간입니다"),

    //item
    NO_EXIST_ITEM(HttpStatus.NOT_FOUND, "ITEM001", "존재하지 않는 아이템입니다");

    //review

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .httpStatus(httpStatus)
                .isSuccess(false)
                .build();
    }
}
