package by.departmentmanager.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Department {
    private Long depId;
    private String depName;
    private Double avgSalary;

    public Double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Double avgSalary) {
        this.avgSalary = avgSalary;
    }

    @JsonCreator
    public Department(@JsonProperty("depId") Long depId, @JsonProperty("depName") String depName, @JsonProperty("avgSalary") Double avgSalary) {
        this.depId = depId;
        this.depName = depName;
        this.avgSalary = avgSalary;
    }

    public Department() {
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

    @Override
    public String toString() {
        return "Department{" +
                "depId=" + depId +
                ", depName='" + depName + '\'' +
                ", avgSalary=" + avgSalary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(depId, that.depId) &&
                Objects.equals(depName, that.depName) &&
                Objects.equals(avgSalary, that.avgSalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depId, depName, avgSalary);
    }
}
