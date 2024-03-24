package tutu.njr10byh.price_statistics.error;

public interface CommonError {
    int getErrCode();

    String getErrMsg();

    CommonError setErrMsg(String errMsg);
}
