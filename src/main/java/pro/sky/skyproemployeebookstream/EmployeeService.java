package pro.sky.skyproemployeebookstream;

public interface EmployeeService {
    String hello();

    String allEmployee();

    String addEmployee(String firstName, String lastName, String passport);

    String removeEmployee(String firstName, String lastName, String passport);

    String findEmployee(String firstName, String lastName, String passport);
}
