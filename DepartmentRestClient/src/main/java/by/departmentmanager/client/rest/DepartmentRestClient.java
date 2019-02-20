package by.departmentmanager.client.rest;

import by.departmentmanager.client.model.Department;

import java.util.List;

public interface DepartmentRestClient {
    public List<Department> getDepartments();
    public Department getDepartment(Long depId);
    public int deleteDepartment(Long depId);
    public int updateDepartment(Department department);
    public int createDepartment(Department department);
}
