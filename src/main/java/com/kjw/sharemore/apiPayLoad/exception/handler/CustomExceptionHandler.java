package com.kjw.sharemore.apiPayLoad.exception.handler;

import com.kjw.sharemore.apiPayLoad.code.BaseErrorCode;
import com.kjw.sharemore.apiPayLoad.exception.GeneralException;

public class CustomExceptionHandler extends GeneralException {

    public CustomExceptionHandler(BaseErrorCode code) {
        super(code);
    }
}
