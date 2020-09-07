package org.lanqiao.service;

import org.lanqiao.dao.UserDao;
import org.lanqiao.entity.User;

/**
 * @author xiaoqaing
 * @date 2020/9/4
 */
public class LoginService {
    public boolean isLoginSuccess(User user){
        UserDao userDao = new UserDao();

        if(userDao.getUser(user).size() > 0){
            return true;
        }
        return false;
    }
}
