package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import root.entity.Employee;
import root.exception_handling.NoSuchEmployeeException;
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
    public Employee getEmployee(@PathVariable("id") String empId ) throws Exception {
        Employee employee = this.employeeService.getEmployeeById(empId);


        if("2".equals(empId)){
            //какой exception - Exception
            throw new Exception("employee with this id not found");
        }

        if(employee == null){
            //какой exception - NoSuchEmployeeException
            throw new NoSuchEmployeeException("employee with this id not found");
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee getAllEmployees(@RequestBody Employee employee){
        System.out.println(employee);
        this.employeeService.saveEmployee(employee);
        return employee;
    }


    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") String empId ){
        employee.setId(empId);
        this.employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") String empId) {
       boolean isDeleted = employeeService.deleteEmployeeById(empId);

       if (isDeleted){
           return ResponseEntity.noContent().build();

       } else {
           return ResponseEntity.notFound().build();
       }
    }
}
