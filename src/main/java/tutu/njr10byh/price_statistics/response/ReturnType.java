package tutu.njr10byh.price_statistics.response;

/**
 * @author baoyuhao
 * @version [1.0]
 * @date 2022/11/23 14:11:10
 * @description
 */
public class ReturnType {

    private int code;

    // 表面对应的请求的返回处理结果"success"或"fail"
    private String status;

    // 若status=success,则data内返回前端需要的json数据
    // 若status=fail，则data内返回通用的错误代码格式
    private Object data;

    // 定义一个通用的创建方法

    public static ReturnType create() {
        return ReturnType.create(200, "success", null);
    }

    public static ReturnType create(Object object) {
        return ReturnType.create(200, "success", object);
    }

    public static ReturnType create(int code, String status, Object object) {
        ReturnType returnType = new ReturnType();
        returnType.setCode(code);
        returnType.setStatus(status);
        returnType.setData(object);
        return returnType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
