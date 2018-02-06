package com.ccfe.property.common.response;

public class DataResponse {

    public static <T> DataWrapper<T> succeed(T data){
        DataWrapper<T> result = new DataWrapper<>();
        result.setCallStatus(CallStatusEnum.SUCCEED);
        result.setErrorCode(ErrorCodeEnum.No_Error);
        result.setData(data);
        return result;
    }

    public static <T> DataWrapper<T> fail(String errorMessage, CallStatusEnum callStatus, ErrorCodeEnum errorCode){
        DataWrapper<T> result = new DataWrapper<>();
        result.setCallStatus(callStatus);
        result.setErrorCode(errorCode);
        result.setErrorMassage(errorMessage);
        return result;
    }
}
