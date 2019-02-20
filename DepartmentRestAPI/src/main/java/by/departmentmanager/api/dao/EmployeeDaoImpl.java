package by.departmentmanager.api.dao;

import by.departmentmanager.api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public  void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = null;
        try{
            employees = jdbcTemplate.query("SELECT * FROM employee",
                    new BeanPropertyRowMapper<Employee>(Employee.class));
        } catch (DataAccessException e){
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public Employee getEmployee(Long empId) {
        Employee employee = null;
        try{
            employee = jdbcTemplate.queryForObject("SELECT * FROM employee WHERE emp_id = ?",
                    new Object[] {empId}, new BeanPropertyRowMapper<Employee>(Employee.class));
        } catch (DataAccessException e){
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public int deleteEmployee(Long empId) {
        int count = jdbcTemplate.update("DELETE FROM employee WHERE emp_id = ?", new Object[] {empId});
        return count;
    }

    @Override
    public int updateEmployee(Employee employee) {
        int count = jdbcTemplate.update("UPDATE employee SET secondname = ?, firstname = ?, patronymic = ?, birthdate = ?, salary = ?, department_dep_id = ? WHERE emp_id = ?",
                new Object[]{employee.getSecondname(), employee.getFirstname(), employee.getPatronymic(), employee.getBirthdate(), employee.getSalary(), employee.getDepartment_dep_id(), employee.getEmpId()});
        return count;
    }

    @Override
    public int createEmployee(Employee employee, Long depId) {
        int count = jdbcTemplate.update("INSERT INTO employee(secondname, firstname, patronymic, birthdate, salary, department_dep_id) VALUES(?, ?, ?, ?, ?, ?)",
                new Object[]{employee.getSecondname(), employee.getFirstname(), employee.getPatronymic(), employee.getBirthdate(), employee.getSalary(), depId});
        return count;
    }

    @Override
    public List<Employee> getEmployeesByDepartment(Long depId) {
        List<Employee> employees = null;
        try{
            employees = jdbcTemplate.query("SELECT * FROM employee WHERE department_dep_id = ?",
                    new Object[] {depId}, new BeanPropertyRowMapper<Employee>(Employee.class));
        } catch (DataAccessException e){
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public List<Employee> getEmployeesByBirthdate(String birthdate) {
        List<Employee> employees = null;
            try{
                employees = jdbcTemplate.query("SELECT * FROM employee WHERE birthdate = ?",
                        new Object[] {birthdate}, new BeanPropertyRowMapper<Employee>(Employee.class));
            } catch (DataAccessException e){
                e.printStackTrace();
            }
        return employees;
    }

    @Override
    public List<Employee> getEmployeesByBirthperiod(String dateFrom, String dateTo) {
        List<Employee> employees = null;
        try{
            employees = jdbcTemplate.query("SELECT * FROM employee WHERE birthdate BETWEEN ? AND ?",
                    new Object[] {dateFrom, dateTo}, new BeanPropertyRowMapper<Employee>(Employee.class));
        } catch (DataAccessException e){
            e.printStackTrace();
        }
        return employees;
    }
}
