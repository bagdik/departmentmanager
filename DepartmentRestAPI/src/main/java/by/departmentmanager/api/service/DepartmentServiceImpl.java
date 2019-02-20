package by.departmentmanager.api.service;

import by.departmentmanager.api.dao.DepartmentDao;
import by.departmentmanager.api.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDao departmentDao;

    public List<Department> getDepartments() {
        List<Department> departments = departmentDao.getDepartments();
        return departments;
    }

    public Department getDepartment(Long depId) {
        Department department = departmentDao.getDepartment(depId);
        return department;
    }

    public int deleteDepartment(Long depId) {
        return departmentDao.deleteDepartment(depId);
    }

    public int updateDepartment(Department department) {
        return departmentDao.updateDepartment(department);
    }

    public int createDepartment(Department department) {
        int count = -1;
        if(department != null){
            count = departmentDao.createDepartment(department);
        }
        return count;
    }
}
