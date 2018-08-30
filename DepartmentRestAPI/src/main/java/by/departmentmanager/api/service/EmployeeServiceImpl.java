package by.departmentmanager.api.service;

import by.departmentmanager.api.dao.EmployeeDao;
import by.departmentmanager.api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeDao.getEmployees();
        return employees;
    }

    @Override
    public Employee getEmployee(Long empId) {
        Employee employee = employeeDao.getEmployee(empId);
        return employee;
    }

    @Override
    public int deleteEmployee(Long empId) {
        return employeeDao.deleteEmployee(empId);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public int createEmployee(Employee employee) {
        return employeeDao.createEmployee(employee);
    }
}
