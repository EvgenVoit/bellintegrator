import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-pdf-form',
  templateUrl: './pdf-form.component.html',
  styleUrls: ['./pdf-form.component.css']
})
export class PdfFormComponent {
  employeeId: number = 0;

  constructor(private employeeService: EmployeeService) {}

  onSubmit() {
    
    if (!this.employeeId) {
      alert('Введите ID сотрудника');
      return;
    } else 

    this.employeeService.generatePDF(this.employeeId).subscribe(
      () => {
        // Скачать PDF файл
        // window.open('http://localhost:4200/pdf');
        location.reload();
        alert("Файл создан, проверьте папку Downloads!");
        
      },
      error => {
        console.error('Ошибка при загрузке PDF файла:', error);
      }
    );
  }
}
