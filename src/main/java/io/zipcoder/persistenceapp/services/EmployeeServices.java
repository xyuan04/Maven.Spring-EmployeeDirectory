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

    public Employee updateEmployeeManager(Long id, Employee manager) {
        Employee ogEmployee = employeeRepository.findOne(id);

        ogEmployee.setManagerID(manager.getEmployeeNumber());

        return employeeRepository.save(ogEmployee);
    }

    public List<Employee> findEmployeesUnderManager(Long id) {
        List<Employee> employeeList = new ArrayList<>();

        List<Employee> allEmp = getAllEmployees();

        for(Employee emp : allEmp) {
            if(emp.getManagerID() == id) {
                employeeList.add(emp);
            }
        }

        return employeeList;
    }

    public List<Employee> findHierarchy(Long id) {
        List<Employee> managerList = new ArrayList<>();
        Employee currentEmployee = employeeRepository.findOne(id);

        while(currentEmployee.getManagerID() != null) {
            managerList.add(employeeRepository.findOne(currentEmployee.getManagerID()));
            currentEmployee = employeeRepository.findOne(currentEmployee.getManagerID());
        }

        return managerList;
    }

    public List<Employee> findSoloWorkers() {
        List<Employee> soloList = new ArrayList<>();

        for(Employee employee : employeeRepository.findAll()) {
            if(employee.getManagerID() == null) {
                soloList.add(employee);
            }
        }

        return soloList;
    }

    public List<Employee> findEmployeesOfDepartment(Long id) {
        List<Employee> departmentEmployees = new ArrayList<>();

        for(Employee employee : employeeRepository.findAll()) {
            if(employee.getDepartmentNumber() == id) {
                departmentEmployees.add(employee);
            }
        }

        return departmentEmployees;
    }

    public List<Employee> findAllMinionsUnder(Long id) {
        List<Employee> minionList = new ArrayList<>();
        Employee currentEmployee = employeeRepository.findOne(id);
        boolean found = true;

        while(found) {
            found = false;
            for(Employee employee : employeeRepository.findAll()) {
                if(employee.getManagerID() == currentEmployee.getEmployeeNumber()) {
                    minionList.add(employee);
                    found = true;
                }
            }
        }

        return minionList;
    }

    //remove a particular employee or list of employees
    public Boolean removeListOfEmployees(List<Employee> employees) {
        for(Employee employee : employees) {
            employeeRepository.delete(employee.getEmployeeNumber());
        }
        return true;
    }

    //remove all employees from a particular department
    public Boolean removeEmployeesFromDepartment(Long id) {
        List<Employee> listOfEmployeesToRemove = findEmployeesOfDepartment(id);

        for(Employee employee : listOfEmployeesToRemove) {
            employeeRepository.delete(employee.getEmployeeNumber());
        }

        return true;
    }

    //remove all employees under a particular manager
    public Boolean removeAllEmployeesUnderManager(Long id) {
        List<Employee> minionList = getAllEmployees();
        Employee manager = getEmployee(id);

        for(Employee employee : minionList) {
            if(employee.getManagerID() == manager.getEmployeeNumber()) {
                employeeRepository.delete(employee.getEmployeeNumber());
            }
        }

        return true;
    }

    //remove all direct reports to a manager. change all employees managed by deleted to next manager up the hierarchy.
    public List<Employee> replaceManagerAndAbsorbEmployees(Long id) {
        List<Employee> absorbedEmployees = new ArrayList<>();
        Employee toBeDeletedEmployee = employeeRepository.findOne(id);

        try {
            Employee replacingManager = employeeRepository.findOne(toBeDeletedEmployee.getManagerID());
            for(Employee employee : getAllEmployees()) {
                if(employee.getManagerID() == toBeDeletedEmployee.getEmployeeNumber()) {
                    employee.setManagerID(replacingManager.getEmployeeNumber());
                    employeeRepository.save(employee);
                    absorbedEmployees.add(employee);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        employeeRepository.delete(toBeDeletedEmployee.getEmployeeNumber());
        return absorbedEmployees;
    }

    //get attributes of an employee
    public String getEmployeeAttributes(Long id) {
        return getEmployee(id).toString();
    }

}
