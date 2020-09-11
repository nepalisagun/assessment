package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface for employee resource operations.
 */
@Repository
public interface EmployeeResource extends JpaRepository<Employee,Integer> {

    Optional<Employee> findByFirstNameAndLastNameAndDateOfBirth(String fName, String lName, String dob);

    Employee findByFirstName(String sam);
}
