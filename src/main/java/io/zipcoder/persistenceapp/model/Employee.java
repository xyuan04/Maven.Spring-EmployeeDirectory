package io.zipcoder.persistenceapp.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long employeeNumber;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "TITLE")
    private String title;
    @Column(name ="PHONE_NUMBER")
    private String phoneNumber;
    @Column(name ="EMAIL")
    private String email;
    @Column(name ="HIRE_DATE")
    private Date hireDate;
    @Column(name ="MANAGER_ID")
    private Long managerID;
    @Column(name ="DEPARTMENT_ID")
    private Long departmentNumber;

    public Long getEmployeeNumber() {
        return employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Long getManagerID() {
        return managerID;
    }

    public void setManagerID(Long managerID) {
        this.managerID = managerID;
    }

    public Long getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(Long departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeNumber, employee.employeeNumber) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(title, employee.title) && Objects.equals(phoneNumber, employee.phoneNumber) && Objects.equals(email, employee.email) && Objects.equals(hireDate, employee.hireDate) && Objects.equals(managerID, employee.managerID) && Objects.equals(departmentNumber, employee.departmentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber, firstName, lastName, title, phoneNumber, email, hireDate, managerID, departmentNumber);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber=" + employeeNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", hireDate=" + hireDate +
                ", managerID=" + managerID +
                ", departmentNumber=" + departmentNumber +
                '}';
    }
}
