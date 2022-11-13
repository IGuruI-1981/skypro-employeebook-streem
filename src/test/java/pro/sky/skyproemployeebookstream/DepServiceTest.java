package pro.sky.skyproemployeebookstream;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.skyproemployeebookstream.exception.EmployeeNotFoundException;
import pro.sky.skyproemployeebookstream.service.DepServiceImpl;
import pro.sky.skyproemployeebookstream.service.EmployeeServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepServiceTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepServiceImpl depService;

    @BeforeEach
    public void beforeEach() {

        List<Employee> employees = List.of(
                new Employee("Иванов", "Виктор", "12334", 5, 54700),
                new Employee("Иванова", "Евгения", "23532", 2, 98650),
                new Employee("Васильев", "Илья", "23452", 1, 78690),
                new Employee("Козлова", "Вероника", "78654", 3, 54785.45),
                new Employee("Педалькина", "Фёкла", "37064", 3, 33465.62),
                new Employee("Петорв", "Иван", "52312", 4, 124643.65),
                new Employee("Панов", "Василий", "32215", 2, 43215.65),
                new Employee("Коновалова", "Елена", "43245", 3, 76543.65),
                new Employee("Ющенко", "Юрий", "53215", 3, 53385.65),
                new Employee("Герасимов", "Иван", "58345", 4, 34785.65));
        when(employeeService.allEmployee()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("emplWithMaxSalaryInDept")
    public void getEmplWithMaxSalaryInDeptPozitivTest(int departament, Employee expected) {
        assertThat(depService.getEmplWithMaxSalaryInDept(departament)).isEqualTo(expected);
    }

    @Test
    public void getEmplWithMaxSalaryInDeptNegativTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> depService.getEmplWithMaxSalaryInDept(6));
    }

    @ParameterizedTest
    @MethodSource("emplWithMinSalaryInDept")
    public void getEmplWithMinSalaryInDeptPozitivTest(int departament, Employee expected) {
        assertThat(depService.getEmplWithMinSalaryInDept(departament)).isEqualTo(expected);
    }

    @Test
    public void getEmplWithMinSalaryInDeptNegativTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> depService.getEmplWithMinSalaryInDept(6));
    }

    @ParameterizedTest
    @MethodSource("AllEmplInDept")
    public void getAllEmplInDeptTest(int departament, List<Employee> expected) {
        assertThat(depService.getAllEmplInDept(departament)).containsExactlyElementsOf(expected);
    }

    @Test
    public void getAllEmplInAllDeptTest() {
        assertThat(depService.getAllEmplInAllDept()).containsAllEntriesOf(
                Map.of(1, List.of(new Employee("Васильев", "Илья", "23452", 1, 78690)),
                        2, List.of(new Employee("Иванова", "Евгения", "23532", 2, 98650), new Employee("Панов", "Василий", "32215", 2, 43215.65)),
                        3, List.of(new Employee("Козлова", "Вероника", "78654", 3, 54785.45), new Employee("Педалькина", "Фёкла", "37064", 3, 33465.62), new Employee("Коновалова", "Елена", "43245", 3, 76543.65), new Employee("Ющенко", "Юрий", "53215", 3, 53385.65)),
                        4, List.of(new Employee("Петорв", "Иван", "52312", 4, 124643.65), new Employee("Герасимов", "Иван", "58345", 4, 34785.65)),
                        5, List.of(new Employee("Иванов", "Виктор", "12334", 5, 54700))));
    }

    public static Stream<Arguments> emplWithMaxSalaryInDept() {
        return Stream.of(
                Arguments.of(1,new Employee("Васильев", "Илья", "23452", 1, 78690)),
                Arguments.of(2,new Employee("Иванова", "Евгения", "23532", 2, 98650)),
                Arguments.of(3,new Employee("Коновалова", "Елена", "43245", 3, 76543.65)),
                Arguments.of(4,new Employee("Петорв", "Иван", "52312", 4, 124643.65)),
                Arguments.of(5,new Employee("Иванов", "Виктор", "12334", 5, 54700)));
    }

    public static Stream<Arguments> emplWithMinSalaryInDept() {
        return Stream.of(
                Arguments.of(1,new Employee("Васильев", "Илья", "23452", 1, 78690)),
                Arguments.of(2,new Employee("Панов", "Василий", "32215", 2, 43215.65)),
                Arguments.of(3,new Employee("Педалькина", "Фёкла", "37064", 3, 33465.62)),
                Arguments.of(4,new Employee("Герасимов", "Иван", "58345", 4, 34785.65)),
                Arguments.of(5,new Employee("Иванов", "Виктор", "12334", 5, 54700)));
    }

    public static Stream<Arguments> AllEmplInDept() {
        return Stream.of(
                Arguments.of(1, List.of(new Employee("Васильев", "Илья", "23452", 1, 78690))),
                Arguments.of(2, List.of(new Employee("Иванова", "Евгения", "23532", 2, 98650), new Employee("Панов", "Василий", "32215", 2, 43215.65))),
                Arguments.of(3, List.of(new Employee("Козлова", "Вероника", "78654", 3, 54785.45), new Employee("Педалькина", "Фёкла", "37064", 3, 33465.62), new Employee("Коновалова", "Елена", "43245", 3, 76543.65), new Employee("Ющенко", "Юрий", "53215", 3, 53385.65)), Arguments.of(4, List.of(new Employee("Петорв", "Иван", "52312", 4, 124643.65), new Employee("Герасимов", "Иван", "58345", 4, 34785.65))),
                Arguments.of(4, List.of(new Employee("Петорв", "Иван", "52312", 4, 124643.65), new Employee("Герасимов", "Иван", "58345", 4, 34785.65))),
                Arguments.of(5, List.of(new Employee("Иванов", "Виктор", "12334", 5, 54700)),
                Arguments.of(6, Collections.emptyList()))));
    }


}
