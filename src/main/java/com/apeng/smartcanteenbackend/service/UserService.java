package com.apeng.smartcanteenbackend.service;

import com.apeng.smartcanteenbackend.entity.Users;

public interface UserService {

    /**
     * 向数据库中添加一个用户，相当于注册成功。
     * @param username 用户名
     * @param password 密码
     * @param role 角色
     */
    void addUser(Users user);

}
