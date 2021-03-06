package by.departmentmanager.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    private Long empId;
    private String secondname;
    private String firstname;
    private String patronymic;
    private String birthdate;
    private Double salary;
    private Long department_dep_id;

    public Long getDepartment_dep_id() {
        return department_dep_id;
    }

    public void setDepartment_dep_id(Long department_dep_id) {
        this.department_dep_id = department_dep_id;
    }

    public Employee() {
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", secondname='" + secondname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", salary=" + salary +
                ", depId=" + department_dep_id +
                '}';
    }
}
