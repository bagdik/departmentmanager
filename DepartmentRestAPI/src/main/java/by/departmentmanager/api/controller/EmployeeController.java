package by.departmentmanager.api.controller;

import by.departmentmanager.api.model.Employee;
import by.departmentmanager.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping(value = "/department/{id}/employees", produces = "application/json")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable("id") Long depId){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<Employee> employees = employeeService.getEmployeesBeDepartment(depId);
        if(employees == null){
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
        httpHeaders.add("Number of records found", String.valueOf(employees.size()));
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping(value = "/department/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long empId) {
        Employee employee = employeeService.getEmployee(empId);
        if (employee == null) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @DeleteMapping(value = "/department/employee/{id}")
    public ResponseEntity<Employee> deleteDepartment(@PathVariable("id") Long empId){
        HttpHeaders headers = new HttpHeaders();
        Employee employee = employeeService.getEmployee(empId);
        if(employee == null){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployee(empId);
        headers.add("Department deleted - ", String.valueOf(empId));
        return new ResponseEntity<Employee>(employee, headers, HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/department/{id}/employee", produces = "application/json")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, @PathVariable("id") Long depId){
        HttpHeaders headers = new HttpHeaders();
        if(employee == null){
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
        }
        employeeService.createEmployee(employee,  depId);
        headers.add("Employee created - ", String.valueOf(employee.getEmpId()));
        return new ResponseEntity<Employee>(employee, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/department/employee/{id}")
    public ResponseEntity<Employee> updateDepartment(@PathVariable("id") Long empId,
                                                       @RequestBody Employee employee){
        HttpHeaders headers = new HttpHeaders();
        Employee isExist = employeeService.getEmployee(empId);
        if(isExist == null){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        } else if (employee == null){
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
        }
        employeeService.updateEmployee(employee);
        headers.add("Employee updated - ", String.valueOf(empId));
        return new ResponseEntity<Employee>(employee, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/department/employees/search/{birthdate}", produces = "application/json")
    public ResponseEntity<List<Employee>> getEmployeesByBirthdate(@PathVariable("birthdate") String birthdate){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<Employee> employees = employeeService.getEmployeesByBirthdate(birthdate);
        if(employees == null){
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
        httpHeaders.add("Number of records found", String.valueOf(employees.size()));
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping(value = "/department/employees/search/{dateFrom}/{dateTo}", produces = "application/json")
    public ResponseEntity<List<Employee>> getEmployeesByBirthperiod(@PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<Employee> employees = employeeService.getEmployeesByBirthperiod(dateFrom, dateTo);
        if(employees == null){
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
        httpHeaders.add("Number of records found", String.valueOf(employees.size()));
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }


}
