package root.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import root.dao.EmployeeDaoInterface;
import root.entity.Employee;

import java.util.List;

@Component
@Service
public class EmployeeService implements EmployeeServiceInterface {

    @Autowired
    private EmployeeDaoInterface employeeDao;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        System.out.println("SERVICE");
        return this.employeeDao.getAllEmployees();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        
        this.employeeDao.saveEmployee(employee);
    }

    @Override
    @Transactional
    public Employee getEmployeeById(String id) {
        return this.employeeDao.getEmployeeById(id);
    }

    @Override
    @Transactional
    public void deleteEmployeeById(String id) {
         this.employeeDao.deleteEmployeeById(id);
    }


}
