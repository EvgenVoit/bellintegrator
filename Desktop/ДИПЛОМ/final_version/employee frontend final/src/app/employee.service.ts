import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Employee } from './employee';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {


 
  private baseURL = "http://localhost:8080/api/v1/employees";

  private salaryURL = "http://localhost:8080/api/v1/employees/salary"

  private quantityURL = "http://localhost:8080/api/v1/employees/departments"

  private quantityEmployeeURL = 'http://localhost:8080/api/v1/employees/quantity'

  private maxSalaryURL = "http://localhost:8080/api/v1/employees/maxSalary"

  private minSalaryURL = "http://localhost:8080/api/v1/employees/minSalary"

  constructor(private httpClient: HttpClient) { }
  
  getEmployeesList(): Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(`${this.baseURL}`);
  }

  addEmployee(employee: Employee): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, employee);
  }

  getEmployeeById(id: number): Observable<Employee>{
    return this.httpClient.get<Employee>(`${this.baseURL}/${id}`);
  }


  updateEmployee(id: number, employee: Employee): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, employee);
  }

  deleteEmployee(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }

  getAverageSalary(): Observable<number> {
    return this.httpClient.get<number>(`${this.salaryURL}`);
  }

  getDepartmentsQuantity() : Observable<number> {
    return this.httpClient.get<number>(`${this.quantityURL}`);
  }

  getEmployeeCount(): Observable<number> {
    return this.httpClient.get<number>(`${this.quantityEmployeeURL}`);
  }

  getMaxSalary(): Observable<number> {
    return this.httpClient.get<number>(`${this.maxSalaryURL}`);
    }

    getMinSalary(): Observable<number> {
      return this.httpClient.get<number>(`${this.minSalaryURL}`);
      }

  generatePDFReport(employeeId: number) {
    return this.httpClient.post('http://localhost:8080/employees/pdf', employeeId, {
      responseType: 'blob' as 'json', // Указываем, что ожидаем тип данных Blob
    });
  }

  generatePDF(employeeId: number): Observable<any> {
    return this.httpClient.post<any>(`${this.baseURL}/pdf/${employeeId}`, {});
  }

}
