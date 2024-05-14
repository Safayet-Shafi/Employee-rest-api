package com.springboot.employee.repository;

import com.springboot.employee.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepo extends JpaRepository<UserInformation,Integer> {
}
