package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.model.Department;
import io.zipcoder.persistenceapp.model.Employee;
import io.zipcoder.persistenceapp.repositories.DepartmentRepository;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServices {
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentServices(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartment(Long id) {
        return departmentRepository.findOne(id);
    }

    public List<Department> getAllDepartments() {
        List<Department> departmentList = new ArrayList<>();
        departmentRepository.findAll().forEach(departmentList::add);

        return departmentList;
    }

    public Department updateDepartment(Long id, Department department) {
        Department ogDepartment = departmentRepository.findOne(id);

        ogDepartment.setName(department.getName());
        ogDepartment.setDepartmentManagerId(department.getDepartmentManagerId());

        return departmentRepository.save(ogDepartment);
    }

    public Boolean deleteDepartment(Long id) {
        try {
            departmentRepository.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Department updateDepartmentManager(Long id, Department department) {
        Department ogDepartment = departmentRepository.findOne(id);

        ogDepartment.setDepartmentManagerId(department.getDepartmentManagerId());

        for(Employee employee : employeeRepository.findAll()) {
            if(employee.getManagerID() == ogDepartment.getDepartmentNumber()) {
                employee.setManagerID(department.getDepartmentManagerId());
                employeeRepository.save(employee);
            }
        }

        return departmentRepository.save(ogDepartment);
    }

    public Department updateDepartmentName(Long id, Department department) {
        Department ogDepartment = departmentRepository.findOne(id);

        ogDepartment.setName(department.getName());

        return departmentRepository.save(ogDepartment);
    }
}
