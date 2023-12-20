package com.kjw.sharemore.apiPayLoad.exception.handler;

import com.kjw.sharemore.apiPayLoad.code.status.ErrorStatus;
import com.kjw.sharemore.apiPayLoad.exception.GeneralException;

public class ReservationExceptionHandler extends GeneralException {

    public ReservationExceptionHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }

    public static class WrongTimeOrder extends ReservationExceptionHandler {
        public WrongTimeOrder() {
            super(ErrorStatus.DATE_TIME_ERROR);
        }
    }

    public static class DuplicateReservation extends ReservationExceptionHandler {
        public DuplicateReservation() {
            super(ErrorStatus.DUPLICATE_RESERVATION);
        }
    }

}
