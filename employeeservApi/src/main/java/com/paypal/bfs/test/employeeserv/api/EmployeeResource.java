package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @RequestMapping(value = "/v1/bfs/employees/{id}", method = RequestMethod.GET)
    ResponseEntity<Employee> employeeGetById(@PathVariable("id") String id);


    // ----------------------------------------------------------
    // TODO - add a new operation for creating employee resource.
    // ----------------------------------------------------------

    @RequestMapping(value= "/v1/bfs/employee", method= RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON},
            produces = {MediaType.APPLICATION_JSON})
    ResponseEntity<Employee> createEmployee(@RequestBody Employee employee);
}
