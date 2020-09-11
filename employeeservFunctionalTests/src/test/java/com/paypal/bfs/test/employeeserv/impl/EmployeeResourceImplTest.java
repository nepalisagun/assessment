package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeResourceImplTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/1", Employee.class);
        System.out.println(employee.getFirstName());
        Assert.assertNotNull(employee);
    }

    @Test
    public void addEmployee() throws Exception {

        Employee employee = new Employee();
        employee.setFirstName("Sam");
        employee.setLastName("Smith");
        employee.setDateOfBirth("01-01-2002");
        Address address = new Address();
        address.setCity("Wichita");
        address.setCountry("USA");
        address.setState("KS");
        address.setZipCode("67208-1718");
        address.setLine1("602 N. Bluff St.");
        address.setLine2("");
        employee.setAddress(address);
        ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl() + "/createEmployee", employee, Employee.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

}