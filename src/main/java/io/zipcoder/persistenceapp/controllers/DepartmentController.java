package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.model.Department;
import io.zipcoder.persistenceapp.services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/department")
public class DepartmentController {
    private DepartmentServices departmentServices;

    @Autowired
    public DepartmentController(DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentServices.createDepartment(department), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartment() {
        return new ResponseEntity<>(departmentServices.getAllDepartments(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        return new ResponseEntity<>(departmentServices.updateDepartment(id, department), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(departmentServices.deleteDepartment(id), HttpStatus.NO_CONTENT);
    }
}
