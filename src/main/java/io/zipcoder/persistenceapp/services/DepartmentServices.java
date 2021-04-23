package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.model.Department;
import io.zipcoder.persistenceapp.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServices {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServices(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
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
            return false;
        }
    }
}
