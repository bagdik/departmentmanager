package by.departmentmanager.api.dao;

import by.departmentmanager.api.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("departmentDao")
public class DepartmentDaoImpl implements DepartmentDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Department> getDepartments() {
        List<Department> departments = null;
        try{
            departments = jdbcTemplate.query("SELECT * FROM department",
                    new BeanPropertyRowMapper<Department>(Department.class));
        } catch (DataAccessException e){
            e.printStackTrace();
        }

        return departments;
    }

    public Department getDepartment(Long depId) {
        Department department = null;
        try{
            department = jdbcTemplate.queryForObject("SELECT * FROM department WHERE dep_id = ?",
                    new Object[] {depId}, new BeanPropertyRowMapper<Department>(Department.class));
        } catch (DataAccessException e){
            e.printStackTrace();
        }
        return department;
    }

    public int deleteDepartment(Long depId) {
        int count = jdbcTemplate.update("DELETE FROM department WHERE dep_id = ?", new Object[] {depId});
        return count;
    }

    public int updateDepartment(Department department) {
        int count = jdbcTemplate.update("UPDATE department SET dep_name = ? WHERE dep_id = ?",
                                            new Object[]{department.getDepName(), department.getDepId()});
        return count;
    }

    public int createDepartment(Department department) {
        int count = jdbcTemplate.update("INSERT INTO department(dep_id, dep_name) VALUES(?, ?)",
                new Object[]{department.getDepId(), department.getDepName()});
        return count;
    }
}
