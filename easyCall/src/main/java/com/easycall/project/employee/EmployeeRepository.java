package com.easycall.project.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.easycall.project.employee.Employee;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByUsername(String username);

    Employee getByUsername(String username);

}
