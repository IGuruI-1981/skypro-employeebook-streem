package pro.sky.skyproemployeebookstream.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.skyproemployeebookstream.service.EmployeeService;

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
        return employeeService.allEmployee().toString();
    }

    @GetMapping(path = "/add")                                         //добавление сотрудника
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("passport") String passport,
                              @RequestParam("departament") int departament,
                              @RequestParam("salary") double salary) {
        return "Added - "+ employeeService.addEmployee(firstName, lastName, passport, departament, salary);
    }

    @GetMapping(path = "/remove")                                      // удаление сотрудника
    public String removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("passport") String passport,
                                 @RequestParam("departament") int departament,
                                 @RequestParam("salary") double salary){
        return "Deleted - " + employeeService.removeEmployee(firstName, lastName, passport,departament,salary);
    }

    @GetMapping(path = "/find")                                      // поиск  сотрудника
    public String findEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("passport") String passport) {
        return "Find - " + employeeService.findEmployee(firstName,lastName,passport).toString();
    }
}
