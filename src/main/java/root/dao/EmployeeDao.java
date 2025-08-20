package root.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import root.entity.Employee;

import java.util.List;

@Repository
@Component
public class EmployeeDao implements EmployeeDaoInterface {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
        System.out.println("REPOSITORY");
        List<Employee> employees = session.createQuery("from Employee", Employee.class).getResultList();
        return employees;

    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        if (!employee.getId().equals("")){
            session.update(employee);
        } else {
            session.save(employee);
        }

    }

    @Override
    public Employee getEmployeeById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class,id);
    }

    @Override
    public void deleteEmployeeById(String id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM Employee" +
                " where id =:employeeId")
                .setParameter("employeeId",id);

        query.executeUpdate();

    }
}
