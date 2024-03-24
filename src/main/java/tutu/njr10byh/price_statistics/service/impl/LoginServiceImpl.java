package tutu.njr10byh.price_statistics.service.impl;

/**
 * @author baoyuhao
 * @version [版本号]
 * @time 2022/12/16 16:47:00
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutu.njr10byh.price_statistics.entity.User;
import tutu.njr10byh.price_statistics.error.BusinessException;
import tutu.njr10byh.price_statistics.error.EmBusinessError;
import tutu.njr10byh.price_statistics.mapper.LoginMapper;
import tutu.njr10byh.price_statistics.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public User getUserInfo(User userinfo) {
        User user = new User();
        user.setId(userinfo.getId());
        user.setUserName(userinfo.getUserName());
        user.setRole(userinfo.getRole());
        return user;
    }

    @Override
    public User loginByAccount(String userName, String passWord)
            throws BusinessException {
        QueryWrapper<User> loginWrapper = new QueryWrapper<User>().eq("pass_word", passWord);
        User user = loginMapper.selectOne(loginWrapper);
        if (user == null) {
            throw new BusinessException(EmBusinessError.RECORD_NOT_EXIST, "用户名或密码错误，登录失败");
        }
        if (user.getUserName().equals(userName)) {
            return this.getUserInfo(user);
        } else {
            throw new BusinessException(EmBusinessError.RECORD_NOT_EXIST, "用户名或密码错误，登录失败");
        }
    }
}
