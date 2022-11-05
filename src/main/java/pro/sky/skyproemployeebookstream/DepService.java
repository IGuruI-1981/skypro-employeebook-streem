package pro.sky.skyproemployeebookstream;

import java.util.List;
import java.util.Map;

public interface DepService {

    Employee getEmplWithMaxSalaryInDept(int departament);

    Employee getEmplWithMinSalaryInDept(int departament);

    List<Employee> getAllEmplInDept(int departament);

    Map<Integer, List<Employee>> getAllEmplInAllDept();
}
