package solution;

import java.util.*;

public class Task2 {

    public static void main(String[] args) {
        Map<String, String> emp1 = new HashMap<>();
        emp1.put("Имя", "Кирилл");
        emp1.put("Возраст", "26");
        emp1.put("Должность", "Middle java dev");
        emp1.put("Зарплата", "150000 руб");

        Map<String, String> emp2 = new HashMap<>();
        emp2.put("Имя", "Виталий");
        emp2.put("Возраст", "28");
        emp2.put("Должность", "Senior java automation dev");
        emp2.put("Зарплата", "2000 $");

        Map<String, String> emp3 = new HashMap<>();
        emp3.put("Имя", "Александр");
        emp3.put("Возраст", "31");
        emp3.put("Должность", "Junior functional tester");
        emp3.put("Зарплата", "50000 руб");

        Map<String, String> emp4 = new HashMap<>();
        emp4.put("Имя", "Дементий");
        emp4.put("Возраст", "35");
        emp4.put("Должность", "dev-ops");
        emp4.put("Зарплата", "1500 $");

        List<Map<String, String>> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);

        String searchKey1 = "Возраст";
        int searchAge = 30;
        List<Map<String, String>> employeesAge = employees.stream()
                .filter(el -> el.containsKey(searchKey1) &&
                        Integer.parseInt(el.get(searchKey1)) < searchAge).toList();
        if (!employeesAge.isEmpty()) {
            System.out.println("Возраст меньше 30 : \n" + employeesAge + "\n");
        } else {
            System.out.println("Данные не найдены!");
        }


        String searchKey2 = "Зарплата";
        List<Map<String, String>> employeesSalary = employees.stream()
                .filter(el -> el.containsKey(searchKey2) &&
                        el.get(searchKey2).contains("руб")).toList();
        if (!employeesSalary.isEmpty()) {
            System.out.println("Получают зарплату в рублях : \n" + employeesSalary + "\n");
        } else {
            System.out.println("Данные не найдены!");
        }

        OptionalDouble averageAge = employees.stream()
                .filter(el -> el.containsKey(searchKey1))
                .mapToInt(el -> Integer.parseInt(el.get(searchKey1)))
                .average();
        if (averageAge.isPresent()) {
            System.out.println("Средний возраст : \n" + (int) averageAge.getAsDouble() + " лет" + "\n");
        } else {
            System.out.println("Данные не найдены!");
        }
    }
}