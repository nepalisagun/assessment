package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Implementation class for employee resource.
 */
@Service
public class EmployeeResourceImpl {

    @Autowired
    private EmployeeResource employeeResource;

    //This is under assumption that each id is unique (it is in current state)
    //However, it is beneficial to retrieve the records as List, to cover one-to-many relationship.
    public ResponseEntity<Employee> employeeGetById(String id) {

        Employee employee = employeeResource.findById(Integer.valueOf(id)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee ID : ".concat(id).concat(" Not Found!!")));

        return new ResponseEntity<>(employee, HttpStatus.FOUND);
    }

    @Transactional
    public ResponseEntity<Employee> employeeCreate(Employee employee) {
        Optional<Employee> employeePresent = employeeResource.findByFirstNameAndLastNameAndDateOfBirth(employee.getFirstName(),
                employee.getLastName(), employee.getDateOfBirth());
        if (!employeePresent.isPresent()) {
            employeeResource.save(employee);
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        } else {
            //Throws 409 response status code for duplicate entries
            throw new ResponseStatusException(HttpStatus.CONFLICT, employee.getFirstName().concat(" ").concat(
                    employee.getLastName()).concat(" already exists"));
        }
    }
}
