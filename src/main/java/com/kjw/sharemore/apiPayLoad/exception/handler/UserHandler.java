package com.kjw.sharemore.apiPayLoad.exception.handler;

import com.kjw.sharemore.apiPayLoad.code.BaseErrorCode;
import com.kjw.sharemore.apiPayLoad.code.status.ErrorStatus;
import com.kjw.sharemore.apiPayLoad.exception.GeneralException;

public class UserHandler extends GeneralException {

    public UserHandler(BaseErrorCode code){
        super(code);
    }

    public static UserHandler notExistUserHandler(){
        return new UserHandler(ErrorStatus.NO_EXIST_USER);
    }


}
