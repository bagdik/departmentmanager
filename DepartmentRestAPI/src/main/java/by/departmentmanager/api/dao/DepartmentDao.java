package by.departmentmanager.api.dao;

import by.departmentmanager.api.model.Department;

import java.util.List;

public interface DepartmentDao {
    public List<Department> getDepartments();
    public Department getDepartment(Long depId);
    public int deleteDepartment(Long depId);
    public int updateDepartment(Department department);
    public int createDepartment(Department department);
}
