package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findEmployeeById(Long id);

    @Query("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)")
    Employee findEmployeeWithMaxSalary();

    @Query("SELECT e FROM Employee e WHERE e.salary = (SELECT MIN(e.salary) FROM Employee e)")
    Employee findEmployeeWithMinSalary();

}
