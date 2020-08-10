package com.diamond.controller;

import com.diamond.result.Result;
import com.diamond.result.ResultFactory;
import com.diamond.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2020/8/10 15:30
 * @Created by lrf
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/api/admin/user")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.userList());
    }

}
