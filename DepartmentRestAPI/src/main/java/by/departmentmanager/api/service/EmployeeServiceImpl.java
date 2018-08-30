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
        return null;
    }

    @Override
    public int deleteEmployee(Long empId) {
        return 0;
    }

    @Override
    public int updateEmployee(Employee employee) {
        return 0;
    }

    @Override
    public int createEmployee(Employee employee) {
        return 0;
    }
}
