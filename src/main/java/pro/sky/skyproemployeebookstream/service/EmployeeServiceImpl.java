package pro.sky.skyproemployeebookstream.service;

import org.springframework.stereotype.Service;
import pro.sky.skyproemployeebookstream.Employee;
import pro.sky.skyproemployeebookstream.exception.EmployeeAlreadyAddedException;
import pro.sky.skyproemployeebookstream.exception.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final ValidatorService validatorService;

    public EmployeeServiceImpl(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }
    List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Иванов", "Виктор", "12334", 5, 54700),
            new Employee("Иванова", "Евгения", "23532", 2, 98650),
            new Employee("Васильев", "Илья", "23452", 1, 78690),
            new Employee("Козлова", "Вероника", "78654", 3, 54785.45),
            new Employee("Педалькина", "Фёкла", "37064", 3, 33465.62),
            new Employee("Петорв", "Иван", "52312", 4, 124643.65),
            new Employee("Панов", "Василий", "32215", 2, 43215.65),
            new Employee("Коновалова", "Елена", "43245", 3, 76543.65),
            new Employee("Ющенко", "Юрий", "53215", 3, 53385.65),
            new Employee("Герасимов", "Иван", "58345", 4, 34785.65)));


    public List<Employee> allEmployee() {
        return new ArrayList<>(employees);
    }

    @Override
    public String addEmployee(String firstName, String lastName, String passport, int departament, double salary) {
        final Employee employeeAdded = new Employee(validatorService.validateFirstName(firstName), validatorService.validateLastName(lastName), passport, departament, salary);
        final List<Employee> findEmpl= (List<Employee>) employees.stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName) && e.getPassport().equals(passport))
                .collect(Collectors.toList());
        if (findEmpl.isEmpty()) {
            employees.add(employeeAdded);
            return employeeAdded.toString();
        } else {
            throw new EmployeeAlreadyAddedException("Такой сотрудник существует ");
        }
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName, String passport) {
        Employee employee = (Employee) employees.stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName) && e.getPassport().equals(passport))
                .findFirst()
                .orElseThrow(() ->new EmployeeNotFoundException("Cотрудник не найден"));

            employees.remove(employee);
            return employee;

    }


    @Override
    public Employee findEmployee(String firstName, String lastName, String passport) {
        return employees.stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName) && e.getPassport().equals(passport))
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));

    }
}
