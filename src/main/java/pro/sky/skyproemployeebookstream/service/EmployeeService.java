package pro.sky.skyproemployeebookstream.service;

import pro.sky.skyproemployeebookstream.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> allEmployee();
    String addEmployee(String firstName, String lastName, String passport, int departament, double salary);
    Employee removeEmployee(String firstName, String lastName, String passport);
    Employee findEmployee(String firstName, String lastName, String passport);
}
