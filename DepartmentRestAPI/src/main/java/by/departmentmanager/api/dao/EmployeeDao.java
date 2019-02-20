package by.departmentmanager.api.dao;

import by.departmentmanager.api.model.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeDao {
    public List<Employee> getEmployees();
    public Employee getEmployee(Long empId);
    public int deleteEmployee(Long empId);
    public int updateEmployee(Employee employee);
    public int createEmployee(Employee employee, Long depId);
    public List<Employee> getEmployeesByDepartment(Long depId);

    public List<Employee> getEmployeesByBirthdate(String birthdate);
    public List<Employee> getEmployeesByBirthperiod(String dateFrom, String dateTo);
}
