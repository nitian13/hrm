package org.nb.hrm.service.impl;


import org.nb.hrm.entity.User;
import org.nb.hrm.mapper.UserMapper;
import org.nb.hrm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserService implements IUserService {

    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void register(String phone, int role) {
        userMapper.register(phone, role);
    }

    @Override
    public User findUser(String phone) {
        return userMapper.findUser(phone);
    }

    @Override
    public User login(String phone, int role) {
        return userMapper.login(phone,role);
    }
}
