package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.EmployeeBadRequestException;
import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for employee resource.
 */
@RestController
@EnableAutoConfiguration
@Configuration
@EnableJpaRepositories("com.paypal.bfs.test.employeeserv.repository")
@EntityScan("com.paypal.bfs.test.employeeserv.api.model")
public class EmployeeResourceImpl implements EmployeeResource {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeResourceImpl(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {

        try{
            Employee employee = employeeService.getEmployee(Integer.parseInt(id));
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch(EmployeeNotFoundException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Employee employee) {

        try{
            Employee obj = employeeService.saveEmployee(employee);
            return new ResponseEntity<>(obj, HttpStatus.CREATED);
        }catch(EmployeeBadRequestException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
