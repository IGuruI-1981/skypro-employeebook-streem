package pro.sky.skyproemployeebookstream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments/")
public class DepController {

    private final DepServiceImpl depServiceImpl;

    public DepController(DepServiceImpl depServiceImpl) {
        this.depServiceImpl = depServiceImpl;
    }

    @GetMapping(path = "/max-salary")                 //поиск сотрудника в отделе с максиальной зарплатой
    public String getEmplWithMaxSalaryInDept(@RequestParam("departamentId") int departament) {
        return depServiceImpl.getEmplWithMaxSalaryInDept(departament).toString();
    }
    @GetMapping(path = "/min-salary")                 //поиск сотрудника в отделе с минимальной зарплатой
    public String getEmplWithMinSalaryInDept(@RequestParam("departamentId") int departament) {
        return depServiceImpl.getEmplWithMinSalaryInDept(departament).toString() ;
    }

    @GetMapping(value = "/all",params = "departamentId")                 //поиск всех  сотрудников в отделе
    public String getAllEmplInDept(@RequestParam("departamentId") int departament) {
        return depServiceImpl.getAllEmplInDept(departament).toString();
    }

    @GetMapping(path = "/all")                 //поиск всех  сотрудников по отделам
    public String getAllEmplInAllDept() {
        return depServiceImpl.getAllEmplInAllDept().toString();
    }
}
