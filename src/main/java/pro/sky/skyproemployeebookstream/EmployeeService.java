package pro.sky.skyproemployeebookstream;

import java.util.List;

public interface EmployeeService {
    String hello();
    List<Employee> allEmployee();
    String addEmployee(String firstName, String lastName, String passport, int departament, double salary);
    String removeEmployee(String firstName, String lastName, String passport,int departament, double salary);
    Employee findEmployee(String firstName, String lastName, String passport);
}
