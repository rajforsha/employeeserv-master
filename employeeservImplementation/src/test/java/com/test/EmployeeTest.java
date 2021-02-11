package com.test;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.impl.EmployeeResourceImpl;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class EmployeeTest extends TestCase {

    EmployeeService mockedEmployeeService = Mockito.mock(EmployeeService.class);
    EmployeeResourceImpl employeeResourceImpl = new EmployeeResourceImpl(mockedEmployeeService);
    Employee employee = null;

    @Before
    public void init() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("shashi");
        employee.setLastName("raj");
        Address address = new Address();
        address.setState("karnataka");
        address.setCountry("India");
        employee.setAddress(address);
    }

    @After
    public void tearDown(){
        employeeResourceImpl = null;
        employee = null;
    }

    @Test
    public void getEmployeeByUnknownIdShouldThrowException(){

        int id = 1;
        Mockito.when(mockedEmployeeService.getEmployee(id)).thenThrow(EmployeeNotFoundException.class);
        employeeResourceImpl.employeeGetById(String.valueOf(id));
    }

    @Test
    public void getEmployeeByUnknownIdShouldReturnEmployee(){

        int id = 1;

        Mockito.when(mockedEmployeeService.getEmployee(id)).thenReturn(employee);
        ResponseEntity<Employee> emp = employeeResourceImpl.employeeGetById(String.valueOf(id));
        assertEquals(emp.getStatusCode().value(), 200);
    }

    @Test
    public void shouldCreateEmployeeAndReturn201StatusCode(){

        int id = 1;
        Mockito.when(mockedEmployeeService.saveEmployee(employee)).thenReturn(employee);
        ResponseEntity<Employee> emp = employeeResourceImpl.createEmployee(employee);
        assertEquals(emp.getStatusCode().value(), 201);
    }

}
