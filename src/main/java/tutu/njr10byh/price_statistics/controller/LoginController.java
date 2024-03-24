package tutu.njr10byh.price_statistics.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tutu.njr10byh.price_statistics.controller.VO.LoginVO;
import tutu.njr10byh.price_statistics.entity.User;
import tutu.njr10byh.price_statistics.error.BusinessException;
import tutu.njr10byh.price_statistics.error.EmBusinessError;
import tutu.njr10byh.price_statistics.response.ReturnType;
import tutu.njr10byh.price_statistics.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/loginByAccount")
    @ApiOperation("通过账号密码登录")
    public ReturnType loginByAccount(@Valid @RequestBody LoginVO loginVO, BindingResult bindingResult,
                                     HttpServletRequest request)
            throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,
                    bindingResult.getFieldError().getDefaultMessage());
        }
        HttpSession session = request.getSession();
        User user = loginService.loginByAccount(loginVO.getUserName(), loginVO.getPassWord());
        session.setAttribute("logined_userInfo", user);
        return ReturnType.create(user);
    }
}
