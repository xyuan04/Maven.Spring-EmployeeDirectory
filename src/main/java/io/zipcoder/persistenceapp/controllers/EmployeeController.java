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

    @GetMapping("/hierarchy/{id}")
    public ResponseEntity<List<Employee>> findHierarchy(@PathVariable Long id) {
        return new ResponseEntity<>(employeeServices.findHierarchy(id), HttpStatus.OK);
    }

    @GetMapping("/solo")
    public ResponseEntity<List<Employee>> findSoloWorkers() {
        return new ResponseEntity<>(employeeServices.findSoloWorkers(), HttpStatus.OK);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<Employee>> findEmployeesOfDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(employeeServices.findEmployeesOfDepartment(id), HttpStatus.OK);
    }

    //Still needs work
    @GetMapping("/allreports/{id}")
    public ResponseEntity<List<Employee>> findAllMinionsUnder(@PathVariable Long id) {
        return new ResponseEntity<>(employeeServices.findAllMinionsUnder(id), HttpStatus.OK);
    }

    //not sure of mapping
    @DeleteMapping("/removelist")
    public ResponseEntity<Boolean> removeListOfEmployees(List<Employee> employees) {
        return new ResponseEntity<>(employeeServices.removeListOfEmployees(employees), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/removedepartment/{id}")
    public ResponseEntity<Boolean> removeEmployeesFromDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(employeeServices.removeEmployeesFromDepartment(id), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("manager/{id}/removeemployees")
    public ResponseEntity<Boolean> removeAllEmployeesUnderManager(@PathVariable Long id) {
        return new ResponseEntity<>(employeeServices.removeAllEmployeesUnderManager(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("manager/{id}/replace")
    public ResponseEntity<List<Employee>> replaceManagerAndAbsorbEmployees(@PathVariable Long id) {
        return new ResponseEntity<>(employeeServices.replaceManagerAndAbsorbEmployees(id), HttpStatus.OK);
    }

    @GetMapping("attributes/{id}")
    public ResponseEntity<String> getEmployeeAttributes(@PathVariable Long id) {
        return new ResponseEntity<>(employeeServices.getEmployeeAttributes(id), HttpStatus.OK);
    }

}
