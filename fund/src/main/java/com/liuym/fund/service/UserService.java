package com.liuym.fund.service;

import com.liuym.fund.dto.UserFormDTO;
import com.liuym.fund.entity.User;

/**
 * created by noob
 * 2020/8/9 20:46
 */
public interface UserService {

    User create(UserFormDTO formDTO);

}
