package com.springboot.employee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.employee.dto.EmployeeDTO;
import com.springboot.employee.dto.ResponseModelDTO;
import com.springboot.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final  EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    ResponseModelDTO postEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.postEmployee(employeeDTO);
    }
    @GetMapping()
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable long id) {
        return employeeService.employeeGetByUniqueId(id);
    }

//    @GetMapping("/employeeId/{empId}")
//    public EmployeeDTO findByEmpId(@PathVariable String empId) {
//        return employeeService.findByEmpId(empId);
//    }

    @GetMapping("/employeeId/{empId}")
    public Map<String, Object> findByEmpId(@PathVariable String empId) {

        Map<String, Object> map = new HashMap<>();
        map.put("EmployeeId", employeeService.findByEmpId(empId));
        return map;
    }

//    DeleteBYResponseEntity
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployeeByIdResponseEntity(@PathVariable(name = "id") long id){

        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Employee deleted successfully.", HttpStatus.OK);
   }

   @DeleteMapping("/{id}")
   public ResponseModelDTO deleteEmployee(@PathVariable long id) {
        return employeeService.deleteEmployeeById(id);
    }


    @PatchMapping("/{id}")
    public ResponseModelDTO updateEmployee(@PathVariable (name = "id") long id,
                                           @RequestBody Map<Object,Object> employeeObj){


        EmployeeDTO employeeDTO= employeeService.employeeGetByUniqueId(id);
        employeeObj.forEach((k,v)->{
            try{
                Field field = ReflectionUtils.findField(EmployeeDTO.class,(String) k);
                field.setAccessible(true);
                ReflectionUtils.setField(field,employeeDTO,v);
            }catch (Exception e){

            }
        });
        return employeeService.updateEmployee(employeeDTO);
    }

    @PutMapping("/put/{id}")
    public ResponseModelDTO updateEmployeePut(@PathVariable (name = "id") long id,
                                              @RequestBody EmployeeDTO employeeDTO){
        return employeeService.updateEmployeePut(employeeDTO,id);

    }


}
