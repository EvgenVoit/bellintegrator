import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';


@Component({
  selector: 'app-salary',
  templateUrl: './salary.component.html',
  styleUrls: ['./salary.component.css']
})
export class SalaryComponent implements OnInit {
  averageSalary: number = 0;
  employeeCount: number = 0;
  departmentCount: number = 0;
  maxSalary: number = 0;
  minSalary: number = 0;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.getAverageSalary();
    this.getEmployeeCount();
    this.getDepartmentCount();
    this.getMaxSalary();
    this.getMinSalary();
  
  }

  getAverageSalary(): void {
    this.employeeService.getAverageSalary()
      .subscribe(averageSalary => this.averageSalary = averageSalary);
  }

  getEmployeeCount(): void {
    this.employeeService.getEmployeeCount()
      .subscribe(employeeCount => this.employeeCount = employeeCount);
  }

  getDepartmentCount(): void {
    this.employeeService.getDepartmentsQuantity()
    .subscribe(departmentCount => this.departmentCount = departmentCount);
  }

  getMaxSalary(): void {
    this.employeeService.getMaxSalary()
    .subscribe(maxSalary => this.maxSalary = maxSalary);
  }
  getMinSalary(): void {
    this.employeeService.getMinSalary()
    .subscribe(minSalary => this.minSalary = minSalary);
  }
}