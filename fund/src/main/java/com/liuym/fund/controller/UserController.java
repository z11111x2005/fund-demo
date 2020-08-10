package com.liuym.fund.controller;

import com.liuym.fund.dto.UserFormDTO;
import com.liuym.fund.entity.User;
import com.liuym.fund.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * created by noob
 * 2020/8/10 12:18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(@Valid @RequestBody UserFormDTO userFormDTO) {
        return userService.create(userFormDTO);
    }
}
