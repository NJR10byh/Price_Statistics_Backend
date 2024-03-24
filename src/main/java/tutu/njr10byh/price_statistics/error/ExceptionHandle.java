package tutu.njr10byh.price_statistics.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tutu.njr10byh.price_statistics.response.ReturnType;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex) {
        int code = 0;
        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            code = businessException.getErrCode();
            responseData.put("errMsg", businessException.getErrMsg());
        }
        return ReturnType.create(code, "fail", responseData);
    }

}
