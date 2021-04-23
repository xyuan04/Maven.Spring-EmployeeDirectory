package io.zipcoder.persistenceapp.model;

import javax.persistence.*;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Long departmentNumber;
    @Column(name ="NAME")
    private String name;
    @Column(name = "EMPLOYEE_ID")
    private Long departmentManagerId;

    public Long getDepartmentNumber() {
        return departmentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentManagerId() {
        return departmentManagerId;
    }

    public void setDepartmentManagerId(Long departmentManagerId) {
        this.departmentManagerId = departmentManagerId;
    }
}
