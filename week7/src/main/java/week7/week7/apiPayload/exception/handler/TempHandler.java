package week7.week7.apiPayload.exception.handler;

import week7.week7.apiPayload.code.BaseErrorCode;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
