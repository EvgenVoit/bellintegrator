package com.example.demo.controller;

import com.example.demo.desc.Department;
import com.example.demo.service.EmployeeService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

import javax.print.Doc;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.itextpdf.text.FontFactory.getFont;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    //get all data
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees/salary")
    public Double getAverageSalary() {
        return employeeService.getAverageSalary();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees/quantity")
    public Integer getEmployeeQuantity() {
        return employeeService.getEmployeeQuantity();
    }

    //create
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }


    // get data by id
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getByID(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee with id " + id + "does not exists"));
        return ResponseEntity.ok(employee);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees/departments")
    public ResponseEntity<Integer> getDepartmentsQuantity() {
        Integer value = Department.values().length;
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees/maxSalary")
    public ResponseEntity<Long> getMaxSalary() {
        Employee emp = employeeService.getMaxEmployeeSalary();
        Long salary = emp.getSalary();
        return new ResponseEntity<>(salary, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees/minSalary")
    public ResponseEntity<Long> getMinSalary() {
        Employee emp = employeeService.getMinEmployeeSalary();
        Long salary = emp.getSalary();
        return new ResponseEntity<>(salary, HttpStatus.OK);
    }


    //update data
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeByID(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee with id " + id + "does not exists"));


        employee.setFname(employeeDetails.getFname());
        employee.setLname(employeeDetails.getLname());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setDesignation(employeeDetails.getDesignation());
        employee.setJoiningDate(employeeDetails.getJoiningDate());
        employee.setSalary(employeeDetails.getSalary());

        Employee updatedEmployee = employeeRepository.save(employee);

        return ResponseEntity.ok(updatedEmployee);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {


        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee with id " + id + "does not exists"));

        employeeRepository.delete(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/employees/pdf/{id}")
    public void generatePDFReport(@PathVariable Long id) throws IOException, DocumentException {

        String filePath = "C:\\Users\\evgen\\Downloads\\employee.pdf";
        Employee employee = employeeService.getEmployeeInfo(id);
        Document doc = new Document();
        FileOutputStream fos = new FileOutputStream(filePath);
        PdfWriter.getInstance(doc, fos);
        doc.open();


        Image image = Image.getInstance("src/main/resources/static/logo.png");
        doc.add(image);

        BaseFont russian = BaseFont.createFont("src/main/resources/static/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(russian, 16);
        Font fontAbout = new Font(russian, 12);
        PdfPTable table = new PdfPTable(6);

        table.setWidthPercentage(100);


        Paragraph chunk = new Paragraph("Личное дело сотрудника " + employee.getFname() + " " + employee.getLname() + ":", font);
        chunk.setAlignment(Element.ALIGN_CENTER);
        chunk.setSpacingAfter(20);
        doc.add(chunk);

        addTableHeader(table);


        table.addCell(new PdfPCell(new Phrase(Long.toString(employee.getId()), font)));
        table.addCell(new PdfPCell(new Phrase(employee.getFname(), font)));
        table.addCell(new PdfPCell(new Phrase(employee.getLname(), font)));
        table.addCell(new PdfPCell(new Phrase(employee.getDesignation(), font)));
        table.addCell(new PdfPCell(new Phrase(employee.getDepartment(), font)));
        table.addCell(new PdfPCell(new Phrase(Double.toString(employee.getSalary()), font)));

        doc.add(table);

        Paragraph aboutTitle = new Paragraph("Характеристика сотрудника: ", font);
        aboutTitle.setAlignment(Element.ALIGN_CENTER);
        aboutTitle.setSpacingAfter(20);
        doc.add(aboutTitle);
        switch (employee.getDepartment()) {
            case "HR" -> {
                Paragraph about = new Paragraph("ФИО - " + employee.getFname() + " " + employee.getLname() + "\n" +
                        "Должность - " + employee.getDesignation() + "\n" + "Дата приема на работу - " + employee.getJoiningDate() +
                        "\n" + "Опыт работы - 5 лет в сфере HR, включая работу в HR агентстве и внутри компании" + "\n" +
                        "Навыки - Аналитическое мышление, умение разрабатывать стратегии управления персоналом, опыт в управлении проектами, знание цифрового маркетинга и социальных медиа." + "\n" +
                        "Срок работы в организации - " + getWorkingPeriod(employee.getJoiningDate()) + " дней", fontAbout);
                about.setAlignment(Element.ALIGN_LEFT);
                about.setSpacingAfter(10);
                doc.add(about);
            }

            case "Marketing" -> {
                Paragraph about = new Paragraph("ФИО - " + employee.getFname() + " " + employee.getLname() + "\n" +
                        "Должность - " + employee.getDesignation() + "\n" + "Дата приема на работу - " + employee.getJoiningDate() +
                        "\n" + "Опыт работы - 5 лет в сфере Marketing, включая работу в marketing агентстве и внутри компании" + "\n" +
                        "Навыки - Аналитическое мышление, умение разрабатывать стратегии управления marketing кампаниями, опыт в управлении проектами, знание цифрового маркетинга и социальных медиа." + "\n" +
                        "Срок работы в организации - " + getWorkingPeriod(employee.getJoiningDate()) + " дней", fontAbout);
                about.setAlignment(Element.ALIGN_LEFT);
                about.setSpacingAfter(10);
                doc.add(about);
            }

            case "IT" -> {
                Paragraph about = new Paragraph("ФИО - " + employee.getFname() + " " + employee.getLname() + "\n" +
                        "Должность - " + employee.getDesignation() + "\n" + "Дата приема на работу - " + employee.getJoiningDate() +
                        "\n" + "Опыт работы - 7 лет в сфере IT, включая работу внутри компании" + "\n" +
                        "Навыки - Аналитическое мышление, умение разрабатывать high-loaded systems, опыт в управлении проектами, знание цифрового маркетинга и социальных медиа." + "\n" +
                        "Срок работы в организации - " + getWorkingPeriod(employee.getJoiningDate()) + " дней", fontAbout);
                about.setAlignment(Element.ALIGN_LEFT);
                about.setSpacingAfter(10);
                doc.add(about);
            }

            case "Finance" -> {
                Paragraph about = new Paragraph("ФИО - " + employee.getFname() + " " + employee.getLname() + "\n" +
                        "Должность - " + employee.getDesignation() + "\n" + "Дата приема на работу - " + employee.getJoiningDate() +
                        "\n" + "Опыт работы - 8 лет в сфере Finance, включая работу внутри компании" + "\n" +
                        "Навыки - Аналитическое мышление, умение разрабатывать financial отчеты, опыт в управлении проектами, знание цифрового учёта финансов." + "\n" +
                        "Срок работы в организации - " + getWorkingPeriod(employee.getJoiningDate()) + " дней", fontAbout);
                about.setAlignment(Element.ALIGN_LEFT);
                about.setSpacingAfter(10);
                doc.add(about);
            }

            case "Building" -> {
                Paragraph about = new Paragraph("ФИО - " + employee.getFname() + " " + employee.getLname() + "\n" +
                        "Должность - " + employee.getDesignation() + "\n" + "Дата приема на работу - " + employee.getJoiningDate() +
                        "\n" + "Опыт работы - 2 года в сфере Building, включая работу внутри компании" + "\n" +
                        "Навыки - Аналитическое мышление, умение практического применения навыков." + "\n" +
                        "Срок работы в организации - " + getWorkingPeriod(employee.getJoiningDate()) + " дней", fontAbout);
                about.setAlignment(Element.ALIGN_LEFT);
                about.setSpacingAfter(10);
                doc.add(about);
            }
        }

        Image image2 = Image.getInstance("src/main/resources/static/data2.jpg");
        image2.scaleAbsolute(550, 150);
        image2.setSpacingAfter(20);


        doc.add(image2);

//
//		doc.add(new Paragraph("Личное дело сотрудника"));
//		doc.add(new Paragraph("Идентификатор: " + employee.getId()));
//		doc.add(new Paragraph("ФИО: " + employee.getFname()));
//		doc.add(new Paragraph("Должность: " + employee.getDesignation()));
//		doc.add(new Paragraph("Отдел: " + employee.getDepartment()));
//		doc.add(new Paragraph("Зарплата "+employee.getSalary()));


        doc.close();
        System.out.println("PDF CREATED SUCCESSFULLY!");

    }

    private void addTableHeader(PdfPTable table) throws DocumentException, IOException {
        BaseFont russian = BaseFont.createFont("src/main/resources/static/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(russian, 12);
        Stream.of("ID", "Имя", "Фамилия", "Должность", "Отдел", "Зарплата")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle, font));
                    header.setBackgroundColor(new BaseColor(255, 200, 0));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    public long getWorkingPeriod(LocalDate dateOfJoin) {
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.DAYS.between(dateOfJoin, currentDate);
    }
}