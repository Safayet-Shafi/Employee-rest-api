package com.springboot.employee.controller;

import com.springboot.employee.model.UserInformation;
import com.springboot.employee.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userinfo")
public class UserController {

    @Autowired
    private UserInformationService userInformationService;

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInformation userInformation) {
        return userInformationService.addUser(userInformation);
    }
}
