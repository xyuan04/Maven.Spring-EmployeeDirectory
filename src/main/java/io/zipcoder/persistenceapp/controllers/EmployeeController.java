package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.model.Employee;
import io.zipcoder.persistenceapp.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/employees")
public class EmployeeController {
    private EmployeeServices employeeServices;

    @Autowired
    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeServices.createEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeServices.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(employeeServices.getEmployee(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeServices.updateEmployee(id, employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(employeeServices.deleteEmployee(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updatemanager/{id}")
    public ResponseEntity<Employee> updateEmployeeManager(@PathVariable Long id, @RequestBody Employee manager) {
        return new ResponseEntity<>(employeeServices.updateEmployeeManager(id, manager), HttpStatus.OK);
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<List<Employee>> findEmployeesUnderManager(@PathVariable Long id) {
        return new ResponseEntity<>(employeeServices.findEmployeesUnderManager(id), HttpStatus.OK);
    }



}
