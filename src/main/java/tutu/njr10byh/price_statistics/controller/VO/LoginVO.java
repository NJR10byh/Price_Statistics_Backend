package tutu.njr10byh.price_statistics.controller.VO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginVO {
    @NotBlank(message = "用户名为空")
    private String userName;

    @NotBlank(message = "密码为空")
    private String passWord;
}
