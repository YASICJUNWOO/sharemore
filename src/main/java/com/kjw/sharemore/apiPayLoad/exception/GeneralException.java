package com.kjw.sharemore.apiPayLoad.exception;

import com.kjw.sharemore.apiPayLoad.code.BaseErrorCode;
import com.kjw.sharemore.apiPayLoad.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
