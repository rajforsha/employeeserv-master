package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {

        Employee employee = new Employee();
        employee.setId(Integer.valueOf(id));
        employee.setFirstName("BFS");
        employee.setLastName("Developer");
        Address address = new Address();
        address.setCity("Bangalore");
        address.setCountry("India");
        address.setState("Karnataka");
        address.setZipCode("560087");
        address.setLine1("KHB  house #64 varthur, Bangalore");
        employee.setAddress(address);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Employee employee) {
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
}
