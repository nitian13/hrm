package org.nb.hrm.service;

import org.nb.hrm.entity.User;


public interface IUserService {
    void register(String phone, int role);
    User findUser(String phone);
    User login(String phone,int role);
}
