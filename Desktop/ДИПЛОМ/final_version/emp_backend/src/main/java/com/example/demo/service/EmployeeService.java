package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;


    public Double getAverageSalary()
    { List<Employee> employees = repository.findAll();
        double totalSalary = 0.0;

        for (Employee employee : employees) {
            totalSalary += employee.getSalary();
        }

        if (employees.size() > 0) {
            return totalSalary / employees.size();
        } else {
            return 0.0;
        }

    }
    public Integer getEmployeeQuantity() {
        return repository.findAll().size();
    }

    public Employee getEmployeeInfo(Long id){
        return repository.findEmployeeById(id);
    }

    public Employee getMaxEmployeeSalary(){
        return repository.findEmployeeWithMaxSalary();
    }

    public Employee getMinEmployeeSalary() {
        return repository.findEmployeeWithMinSalary();
    }
}
