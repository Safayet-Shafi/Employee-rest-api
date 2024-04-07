package com.springboot.employee.service;

import com.springboot.employee.dto.EmployeeDTO;
import com.springboot.employee.dto.ResponseModelDTO;

import java.util.List;

public interface EmployeeService {
    ResponseModelDTO postEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployees ();

    EmployeeDTO employeeGetByUniqueId(long id);

    EmployeeDTO findByEmpId(String empId);
    //    DeleteBYResponseEntity
    void deleteEmployeeByIdResponseEntity(long id);

    ResponseModelDTO deleteEmployeeById(long id);
    //Patch Update
    ResponseModelDTO updateEmployee(EmployeeDTO employeeDTO);
    //Put Update
    ResponseModelDTO updateEmployeePut(EmployeeDTO employeeDTO,long id);
}
