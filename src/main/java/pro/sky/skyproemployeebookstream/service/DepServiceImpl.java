package pro.sky.skyproemployeebookstream.service;

import org.springframework.stereotype.Service;
import pro.sky.skyproemployeebookstream.Employee;
import pro.sky.skyproemployeebookstream.exception.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepServiceImpl implements DepService {
    private final EmployeeService employeeService;

    public DepServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmplWithMaxSalaryInDept(int departament) {
        return employeeService.allEmployee().stream()
                .filter(d -> d.getDepartament() == departament)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Такой департамент не найден"));
    }

    @Override
    public Employee getEmplWithMinSalaryInDept(int departament) {
        return employeeService.allEmployee().stream()
                .filter(d -> d.getDepartament() == departament)
                .min(Comparator.comparing(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Такой департамент не найден"));
    }

    @Override
    public List<Employee> getAllEmplInDept(int departament) {
        return employeeService.allEmployee().stream()
                .filter(d -> d.getDepartament() == departament)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmplInAllDept() {
        return employeeService.allEmployee().stream()
                .collect(Collectors.groupingBy(Employee::getDepartament));
    }
}