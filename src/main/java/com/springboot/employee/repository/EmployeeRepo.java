package com.springboot.employee.repository;

import com.springboot.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Employee findByEmpId(String empId);
}
