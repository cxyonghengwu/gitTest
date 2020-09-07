package org.lanqiao.dao;

import org.lanqiao.entity.User;

import java.util.List;

/**
 * @author xiaoqaing
 * @date 2020/9/4
 */
public class UserDao extends BaseDao<User>{

    public List<User> getUser(User user) {
        return executeQuery("select account,password from user where account = ? and password = ?", new Object[]{user.getAccount(), user.getPassword()});
    }
}
