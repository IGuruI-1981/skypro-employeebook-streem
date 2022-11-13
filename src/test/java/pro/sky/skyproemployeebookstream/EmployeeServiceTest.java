package pro.sky.skyproemployeebookstream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.skyproemployeebookstream.exception.EmployeeAlreadyAddedException;
import pro.sky.skyproemployeebookstream.exception.EmployeeNotFoundException;
import pro.sky.skyproemployeebookstream.exception.IncorrectNameOfEmployee;
import pro.sky.skyproemployeebookstream.service.EmployeeServiceImpl;
import pro.sky.skyproemployeebookstream.service.ValidatorService;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class EmployeeServiceTest {

    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl(new ValidatorService());

    @AfterEach
    @BeforeEach
    public void afterEach() {
        employeeService.allEmployee().forEach(employee -> employeeService.removeEmployee(employee.getFirstName(),
                                                                                        employee.getLastName(),
                                                                                        employee.getPassport()));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void addEmployeeNegativTest(String firstName, String lastName, String passport, int departament, double salary) {
        Employee excepted = new Employee(firstName, lastName, passport, departament, salary);
        assertThat(employeeService.allEmployee()).isEmpty();
                    employeeService.addEmployee(firstName,lastName,passport,departament,salary);
        assertThat(employeeService.allEmployee())
                .hasSize(1)
                .contains(excepted);
        assertThat(employeeService.findEmployee(excepted.getFirstName(), excepted.getLastName(), excepted.getPassport()))
                .isNotNull()
                .isEqualTo(excepted);
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.addEmployee(firstName, lastName, passport, departament, salary));
    }

    @Test
    public void addEmployeeNegativTest2() {
        assertThatExceptionOfType(IncorrectNameOfEmployee.class)
                .isThrownBy(()-> employeeService.addEmployee("Иванов2", "Виктор", "12334", 5, 54700));

        assertThatExceptionOfType(IncorrectNameOfEmployee.class)
                .isThrownBy(()-> employeeService.addEmployee("Петорв", "#Иван", "52312", 4, 124643.65));

        assertThatExceptionOfType(IncorrectNameOfEmployee.class)
                .isThrownBy(()-> employeeService.addEmployee(null,"Василий" , "23452", 1, 78690));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void addEmployeePozitivTest(String firstName, String lastName, String passport, int departament, double salary) {
    }

    @ParameterizedTest
    @MethodSource("params")
    public void removeEmployeeNegativTest(String firstName, String lastName, String passport, int departament, double salary) {
        assertThat(employeeService.allEmployee()).isEmpty();
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.removeEmployee("rand","rand","rund"));

        Employee excepted = new Employee(firstName, lastName, passport, departament, salary);
        employeeService.addEmployee(firstName, lastName, passport, departament, salary);
        assertThat(employeeService.allEmployee())
                .hasSize(1)
                .containsExactly(excepted);

        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.removeEmployee("rand","rand","rund"));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void removeEmployeePozitivTest(String firstName, String lastName, String passport, int departament, double salary) {
        assertThat(employeeService.allEmployee()).isEmpty();
        Employee excepted = new Employee(firstName, lastName, passport, departament, salary);
        employeeService.addEmployee(firstName, lastName, passport, departament, salary);
        assertThat(employeeService.allEmployee())
                .hasSize(1)
                .containsExactly(excepted);
        assertThat(employeeService.removeEmployee(firstName,lastName,passport)).isEqualTo(excepted);
        assertThat(employeeService.allEmployee().isEmpty());
    }


    @ParameterizedTest
    @MethodSource("params")
    public void findEmployeeNegativTest(String firstName, String lastName, String passport, int departament, double salary) {
        assertThat(employeeService.allEmployee()).isEmpty();
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.findEmployee("rand","rand","rund"));

        Employee excepted = new Employee(firstName, lastName, passport, departament, salary);
        employeeService.addEmployee(firstName, lastName, passport, departament, salary);
        assertThat(employeeService.allEmployee())
                .hasSize(1)
                .containsExactly(excepted);

        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.findEmployee("rand","rand","rund"));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void findEmployeePozitivTest(String firstName, String lastName, String passport, int departament, double salary) {
        assertThat(employeeService.allEmployee()).isEmpty();
        Employee excepted = new Employee(firstName, lastName, passport, departament, salary);
        employeeService.addEmployee(firstName, lastName, passport, departament, salary);
        assertThat(employeeService.allEmployee())
                .hasSize(1)
                .containsExactly(excepted);
        assertThat(employeeService.findEmployee(firstName,lastName,passport)).isEqualTo(excepted);
    }

    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Иванов", "Виктор", "12334", 5, 54700),
                Arguments.of("Петорв", "Иван", "52312", 4, 124643.65),
                Arguments.of("Васильев", "Илья", "23452", 1, 78690));
    }
}



