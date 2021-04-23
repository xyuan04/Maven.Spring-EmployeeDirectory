package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.model.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServices {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServices(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findOne(id);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        employeeRepository.findAll().forEach(employeeList::add);

        return employeeList;
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee ogEmployee = employeeRepository.findOne(id);

        ogEmployee.setFirstName(employee.getFirstName());
        ogEmployee.setLastName(employee.getLastName());
        ogEmployee.setTitle(employee.getTitle());
        ogEmployee.setPhoneNumber(employee.getPhoneNumber());
        ogEmployee.setEmail(employee.getEmail());
        ogEmployee.setHireDate(employee.getHireDate());
        ogEmployee.setManagerID(employee.getManagerID());
        ogEmployee.setDepartmentNumber(employee.getDepartmentNumber());

        return employeeRepository.save(ogEmployee);
    }

    public Boolean deleteEmployee(Long id) {
        employeeRepository.delete(id);
        return true;
    }
}
