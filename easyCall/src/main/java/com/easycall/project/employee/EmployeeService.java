package com.easycall.project.employee;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public boolean updateEmployeePassword(Employee employee, String oldPassword, String newPassword) {
        // Verifica que la contraseña antigua sea correcta
        if (employee.getPassword().equals(oldPassword)) {
            employee.setPassword(newPassword);
            employeeRepository.save(employee);
            return true; // Cambio de contraseña exitoso
        }
        return false; // Cambio de contraseña fallido
    }
}
