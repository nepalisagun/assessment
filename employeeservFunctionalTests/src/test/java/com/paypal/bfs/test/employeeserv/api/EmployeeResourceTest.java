package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeResourceTest extends TestCase {
    @Autowired
    EmployeeResource employeeResource;

    @Test
    public void createEmployee() {

        Employee employee = new Employee(1, "Sam", "Smith", "01-01-2002", new Address("602 N Oliver", "", "Wichita", "KS", "USA", "67208-1418"));
        employeeResource.save(employee);
        Employee employee2 = employeeResource.findByFirstName("Sam");
        assertNotNull(employee);
        assertEquals(employee2.getFirstName(), employee.getFirstName());
        assertEquals(employee2.getLastName(), employee.getLastName());
    }



    @Test
    public void testGetEmployee() {

        Employee employee = new Employee(1, "Sam", "Smith", "01-01-2002", new Address("602 N Oliver", "", "Wichita", "KS", "USA", "67208-1418"));
        employeeResource.save(employee);
        Optional<Employee> employee2 = employeeResource.findById(1);
        assertNotNull(employee);
        employee2.ifPresent(emp2->assertEquals(emp2.getFirstName(), employee.getFirstName()));
        employee2.ifPresent(emp2->assertEquals(emp2.getLastName(), employee.getLastName()));
    }
}