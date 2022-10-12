package pro.sky.skyproemployeebookstream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee/")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/hello")
    public String hello() {
        return employeeService.hello();
    }

    @GetMapping(path = "/allEmployee")                                         //все сотрудники
    public String allEmployee() {
        return employeeService.allEmployee();
    }

    @GetMapping(path = "/add")                                         //добавление сотрудника
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("passport") String passport) {
        return "Employee added" +  employeeService.addEmployee(firstName, lastName, passport);
    }

    @GetMapping(path = "/remove")                                      // удаление сотрудника
    public String removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("passport") String passport) {
        return "Employee deleted" + employeeService.removeEmployee(firstName,lastName, passport);
    }

    @GetMapping(path = "/find")                                      // поиск  сотрудника
    public String findEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("passport") String passport) {
        return "Employee find" + employeeService.findEmployee(firstName,lastName, passport);
    }

}
