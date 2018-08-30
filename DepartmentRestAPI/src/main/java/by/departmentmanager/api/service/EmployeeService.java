package by.departmentmanager.api.service;

import by.departmentmanager.api.model.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getEmployees();
    public Employee getEmployee(Long empId);
    public int deleteEmployee(Long empId);
    public int updateEmployee(Employee employee);
    public int createEmployee(Employee employee);
}
