package com.easycall.project.employee;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Otros métodos de lógica al programa

    public boolean changePassword(Employee employee, String newPassword) {
        try {
            // Cambia la contraseña utilizando la nueva proporcionada por el empleado
            employee.setPassword(newPassword);
            repository.save(employee);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public Optional<Employee> authenticate(String username, String password) {
        // Busca el empleado por nombre de usuario y verifica la contraseña
        return repository.findByUsername(username)
                .filter(e -> e.getPassword().equals(password));
    }

    public Optional<Employee> getEmployeeByUsername(String username) {
        // Obtén el empleado por nombre de usuario sin autenticación
        return repository.findByUsername(username);
    }
}
