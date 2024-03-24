package tutu.njr10byh.price_statistics.error;

public enum EmBusinessError implements CommonError {
    // 通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOWN_ERROR(10002, "内部错误"),
    DUPLICATE_KEY_EXCEPTION(10003, "数据重复"),
    CANNOT_BE_MODIFIED(10004, "当前不可修改"),
    RECORD_NOT_EXIST(10005, "数据不存在"),
    PROCESS_ERROR(10006, "流程错误");

    EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;

    private String errMsg;

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
