package com.liuym.fund.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuym.fund.dao.UserMapper;
import com.liuym.fund.dto.UserFormDTO;
import com.liuym.fund.entity.User;
import com.liuym.fund.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created by noob
 * 2020/8/9 20:46
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User create(UserFormDTO formDTO) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", formDTO.getUserName());
        User user = userMapper.selectOne(wrapper);
        if (null != user) {
            return user;
        }
        User newUser = new User();
        newUser.setUserName(formDTO.getUserName());
        userMapper.insert(newUser);
        return newUser;
    }
}
