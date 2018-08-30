package by.departmentmanager.api.controller;

import by.departmentmanager.api.model.Employee;
import by.departmentmanager.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/department/employees", produces = "application/json")
    public ResponseEntity<List<Employee>> getEmployees(){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<Employee> employees = employeeService.getEmployees();
        if(employees == null){
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
        httpHeaders.add("Number of records found", String.valueOf(employees.size()));
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
}
