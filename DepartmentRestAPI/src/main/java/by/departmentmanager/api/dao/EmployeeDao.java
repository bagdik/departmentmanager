package by.departmentmanager.api.dao;

import by.departmentmanager.api.model.Employee;

import java.util.List;

public interface EmployeeDao {
    public List<Employee> getEmployees();
    public Employee getEmployee(Long empId);
    public int deleteEmployee(Long empId);
    public int updateEmployee(Employee employee);
    public int createEmployee(Employee employee);
}
