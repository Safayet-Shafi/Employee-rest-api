package com.springboot.employee.service;

import com.springboot.employee.model.UserInformation;
import com.springboot.employee.repository.UserInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInformationService {

    @Autowired
    private UserInformationRepo userInformationRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(UserInformation userInformation) {
        userInformation.setPassword(passwordEncoder.encode(userInformation.getPassword()));
        userInformationRepo.save(userInformation);
        return "user added to system ";
    }
}
