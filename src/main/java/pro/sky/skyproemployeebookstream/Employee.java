package pro.sky.skyproemployeebookstream;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private String passport;
    private int departament;
    private double salary;


    public Employee(String firstName, String lastName, String passport, int departament, double salary) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.passport = passport;
        this.departament = departament;
        this.salary = salary;

    }

    public String getPassport() {
        return passport;
    }

    public int getDepartament() {
        return departament;
    }

    public double getSalary() {
        return salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, passport);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passport + '\'' +
                ", departament=" + departament +
                ", salary=" + salary +
                '}';
    }
}
