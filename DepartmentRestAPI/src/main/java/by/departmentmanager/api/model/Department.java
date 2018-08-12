package by.departmentmanager.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Department {
    private Long depId;
    private String depName;

    @JsonCreator
    public Department(@JsonProperty("depId") Long depId, @JsonProperty("depNane") String depName) {
        this.depId = depId;
        this.depName = depName;
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
                '}';
    }
}
