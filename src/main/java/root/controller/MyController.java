package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import root.entity.Employee;
import root.exception_handling.EmployeeIncorrectData;
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
            //какой exception - NoSuchEmployeeException
            throw new Exception("employee with this id not found");
        }

        if(employee == null){
            //какой exception - NoSuchEmployeeException
            throw new NoSuchEmployeeException("employee with this id not found");
        }
        return employee;
    }

    @ExceptionHandler
    //работа с NoSuchEmployeeException
    public ResponseEntity<EmployeeIncorrectData> handleException(NoSuchEmployeeException exception){
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    //общий Exception
    public ResponseEntity<EmployeeIncorrectData> handleException(Exception exception){
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
