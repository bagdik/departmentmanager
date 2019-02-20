package by.departmentmanager.client.model;

public class Department {
    private Long depId;
    private String depName;
    private Double avgSalary;

    public Department() {
    }

    public Department(Long depId, String depName, Double avgSalary) {
        this.depId = depId;
        this.depName = depName;
        this.avgSalary = avgSalary;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Double avgSalary) {
        this.avgSalary = avgSalary;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depId=" + depId +
                ", depName='" + depName + '\'' +
                ", avgSalary=" + avgSalary +
                '}';
    }
}
