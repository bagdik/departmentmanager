package by.departmentmanager.api.controller;

import by.departmentmanager.api.model.Department;
import by.departmentmanager.api.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    private static final Logger logger = Logger.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/department", produces = "application/json")
    public ResponseEntity<List<Department>> getDepartments(){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<Department> departments = departmentService.getDepartments();
        logger.info("Bean departmentService has been autowired");
        if(departments == null){
            return new ResponseEntity<List<Department>>(HttpStatus.NOT_FOUND);
        }
        httpHeaders.add("Number of records found", String.valueOf(departments.size()));
        return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
    }

    @GetMapping(value = "/department/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") Long depId) {
        Department department = departmentService.getDepartment(depId);
        if (department == null) {
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Department>(department, HttpStatus.OK);
    }

    @DeleteMapping(value = "/department/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable("id") Long depId){
        HttpHeaders headers = new HttpHeaders();
        Department department = departmentService.getDepartment(depId);
        if(department == null){
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
        }
        departmentService.deleteDepartment(depId);
        headers.add("Department deleted - ", String.valueOf(depId));
        return new ResponseEntity<Department>(department, headers, HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/department", produces = "application/json")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department){
        HttpHeaders headers = new HttpHeaders();
        if(department == null){
            return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
        }
        departmentService.createDepartment(department);
        headers.add("Department created - ", String.valueOf(department.getDepId()));
        return new ResponseEntity<Department>(department, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/department/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long depId,
                                                       @RequestBody Department department){
        HttpHeaders headers = new HttpHeaders();
        Department isExist = departmentService.getDepartment(depId);
        if(isExist == null){
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
        } else if (department == null){
            return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
        }
        departmentService.updateDepartment(department);
        headers.add("Department updated - ", String.valueOf(depId));
        return new ResponseEntity<Department>(department, headers, HttpStatus.OK);
    }
}
