package com.springboot.employee.service.serviceImpl;

import com.springboot.employee.dto.EmployeeDTO;
import com.springboot.employee.dto.ResponseModelDTO;
import com.springboot.employee.exception.ResourceNotFoundException;
import com.springboot.employee.model.Employee;
import com.springboot.employee.repository.EmployeeRepo;
import com.springboot.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, ModelMapper mapper) {
        this.employeeRepo = employeeRepo;
        this.mapper = mapper;
    }

    @Override
    public ResponseModelDTO postEmployee(EmployeeDTO employeeDTO) {
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();
        Employee employee = maptoEntity(employeeDTO);
        try{
            Employee employee1 = employeeRepo.save(employee);
            responseModelDTO.setResponseCode(1);
            responseModelDTO.setResponseMessage("SuccessFull");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return responseModelDTO;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees=employeeRepo.findAll();
        return employees.stream()  // Convert the collection 'employees' into a Stream
                .map(employee -> maptoDTO(employee))  // Map each 'employee' object to its corresponding DTO using the 'mapToDTO' function
                .toList();  // Convert the resulting Stream into a List
    }

    @Override
    public EmployeeDTO employeeGetByUniqueId(long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        EmployeeDTO employeeDTO= maptoDTO(employee);
        return employeeDTO;
    }

    @Override
    public EmployeeDTO findByEmpId(String empId) {
        Employee employee = employeeRepo.findByEmpId(empId);
        EmployeeDTO employeeDTO= maptoDTO(employee);
        return employeeDTO;
    }



    @Override
    public ResponseModelDTO deleteEmployeeById(long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();

        try{
            employeeRepo.delete(employee);
            responseModelDTO.setResponseCode(1);
            responseModelDTO.setResponseMessage("Employee Deteted SuccessFully");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return responseModelDTO;
    }

    //    DeleteBYResponseEntity
    @Override
    public void deleteEmployeeByIdResponseEntity(long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepo.delete(employee);
    }
    //Patch Update
    @Override
    public ResponseModelDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = maptoEntity(employeeDTO);
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();
        try{
            Employee employee1 = employeeRepo.save(employee);
            responseModelDTO.setResponseCode(1);
            responseModelDTO.setResponseMessage("Updated Data SuccessFull");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return responseModelDTO;
    }
    //put Update
    @Override
    public ResponseModelDTO updateEmployeePut(EmployeeDTO employeeDTO, long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        employee.setEmpId(employeeDTO.getEmpId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setMobile(employeeDTO.getMobile());
        employee.setEmail(employeeDTO.getEmail());
        employee.setSalary(employeeDTO.getSalary());

        ResponseModelDTO responseModelDTO = new ResponseModelDTO();

        try{
            Employee employee1 = employeeRepo.save(employee);
            responseModelDTO.setResponseCode(1);
            responseModelDTO.setResponseMessage("Updated Data SuccessFull");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return responseModelDTO;
    }


    private EmployeeDTO maptoDTO(Employee employee) {
        EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
        return employeeDTO;
    }

    private Employee maptoEntity(EmployeeDTO employeeDTO) {
        Employee employee = mapper.map(employeeDTO, Employee.class);
        return employee;
    }
}
