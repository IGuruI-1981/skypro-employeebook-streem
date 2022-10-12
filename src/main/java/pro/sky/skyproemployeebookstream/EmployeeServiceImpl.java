package pro.sky.skyproemployeebookstream;

import org.springframework.stereotype.Service;
import pro.sky.skyproemployeebookstream.exception.EmployeeAlreadyAddedException;
import pro.sky.skyproemployeebookstream.exception.EmployeeNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Map<String, Employee> employees = new HashMap<>(Map.of(
            "12334", new Employee("Иванов", "Виктор", "12334", 5, 54700),
            "23532", new Employee("Иванова", "Евгения", "23532", 2, 98650),
            "23452", new Employee("Васильев", "Илья", "23452", 1, 78690 )));

    public String hello() {
        return "HelloSkyPRO";
    }

    public String allEmployee() {
        return employees.toString();
    }

    @Override
    public String addEmployee(String firstName, String lastName, String passport) {
        return null;
    }

    @Override
    public String addEmployee(String firstName, String lastName, String passport, int departament, double salary) {
        Employee employee = new Employee(firstName, lastName, passport, departament, salary );
        final Employee empl = employees.get(passport);
        if (empl == null) {
            employees.put(passport, employee);
            return employees.get(passport).toString();
        }
        else {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть ");
        }
    }

    @Override
    public String removeEmployee(String firstName, String lastName, String passport) {
        //Employee employee = new Employee(firstName, lastName, passport);
        final Employee empl = employees.get(passport);
        if (empl == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employees.remove(passport);
        return empl.toString();
    }


    @Override
    public String findEmployee(String firstName, String lastName, String passport) {
        // Employee employee = new Employee(firstName, lastName, passport);
        final Employee empl = employees.get(passport);
        if (empl == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return empl.toString();
    }
}
