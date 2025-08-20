package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import root.entity.Employee;
import root.service.EmployeeService;
import root.service.EmployeeServiceInterface;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private EmployeeServiceInterface employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        List<Employee> employees = this.employeeService.getAllEmployees();
        return employees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") String empId ){
        List<Employee> employees = this.employeeService.getAllEmployees();
        Employee employee = this.employeeService.getEmployeeById(empId);
        return employee;
    }
}
