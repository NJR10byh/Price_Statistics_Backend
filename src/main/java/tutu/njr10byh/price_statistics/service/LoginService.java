package tutu.njr10byh.price_statistics.service;

import tutu.njr10byh.price_statistics.entity.User;
import tutu.njr10byh.price_statistics.error.BusinessException;

public interface LoginService {
    User loginByAccount(String userName, String passWord)
            throws BusinessException;
}
