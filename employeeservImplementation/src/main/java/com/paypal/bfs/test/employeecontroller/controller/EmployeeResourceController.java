package com.paypal.bfs.test.employeecontroller.controller;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.impl.EmployeeResourceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class EmployeeResourceController {

    @Autowired
    private EmployeeResourceImpl employeeResourceImpl;

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") String id) {
        return employeeResourceImpl.employeeGetById(id);
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        System.out.println(employee);
        return employeeResourceImpl.employeeCreate(employee);
    }
}
