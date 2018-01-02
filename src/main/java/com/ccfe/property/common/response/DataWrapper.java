package com.ccfe.property.common.response;


public class DataWrapper<T>  {
    private CallStatusEnum callStatus;
    private ErrorCodeEnum errorCode;
    private String errorLabel;
    private T data;
    private Page page;

    public DataWrapper() {
        setCallStatus(CallStatusEnum.SUCCEED);
        setErrorCode(ErrorCodeEnum.No_Error);
    }

    public CallStatusEnum getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(CallStatusEnum callStatus) {
        this.callStatus = callStatus;
    }

    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
        if (!errorCode.equals(ErrorCodeEnum.No_Error)) {
            this.callStatus = CallStatusEnum.FAILED;
        }
        this.errorLabel = errorCode.getLabel();
    }

    public String getErrorLabel() {
        return errorLabel;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return	"Code:" + this.callStatus + "\n" +
                "Error Code:" + this.errorCode+ "\n";
    }
}
