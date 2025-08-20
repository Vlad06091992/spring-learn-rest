package root.service;

import root.entity.Employee;

import java.util.List;

public interface EmployeeServiceInterface {
    public List<Employee> getAllEmployees();
    public void saveEmployee(Employee employee);
    public Employee getEmployeeById(String id);
    public void deleteEmployeeById(String id);
}
