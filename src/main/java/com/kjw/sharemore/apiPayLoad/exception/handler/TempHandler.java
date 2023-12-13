package com.kjw.sharemore.apiPayLoad.exception.handler;

import com.kjw.sharemore.apiPayLoad.code.BaseErrorCode;
import com.kjw.sharemore.apiPayLoad.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode code) {
        super(code);
    }

}
