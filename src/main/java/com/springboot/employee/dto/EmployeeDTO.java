package com.springboot.employee.dto;


import lombok.Data;

@Data
public class EmployeeDTO {

    private long id;
    private String empId;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String salary;
}
