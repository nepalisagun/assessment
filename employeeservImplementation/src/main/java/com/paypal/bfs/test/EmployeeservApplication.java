package com.paypal.bfs.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.paypal.bfs.test.*", " com.paypal.bfs.test.employeeserv.impl.*", "com.paypal.bfs.test.employeeserv.api.*", "com.paypal.bfs.test.employeeserv.api.model.*"})
@EntityScan({"com.paypal.bfs.test.employeeserv.api.model"})
@EnableJpaRepositories({"com.paypal.bfs.test.employeeserv.api"})
public class EmployeeservApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeservApplication.class, args);
    }
}