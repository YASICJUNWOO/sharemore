package com.kjw.sharemore.apiPayLoad.exception.handler;

import com.kjw.sharemore.apiPayLoad.code.BaseErrorCode;
import com.kjw.sharemore.apiPayLoad.code.status.ErrorStatus;
import com.kjw.sharemore.apiPayLoad.exception.GeneralException;

public class UserExceptionHandler extends GeneralException {

    public UserExceptionHandler(BaseErrorCode code) {
        super(code);
    }

    //유저 없을 때
    public static class NoExistUser extends UserExceptionHandler {
        public NoExistUser() {
            super(ErrorStatus.NO_EXIST_USER);
        }
    }

    //유저 이미 존재할 때
    public static class ExistUser extends UserExceptionHandler {
        public ExistUser() {
            super(ErrorStatus.EXSIST_USER);
        }
    }

}