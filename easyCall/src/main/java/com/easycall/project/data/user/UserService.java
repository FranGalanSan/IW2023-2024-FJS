package com.easycall.project.data.user;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    public UserRepository userRepository;

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public boolean createUser(String firstName, String lastName, String email, String password) {
        // Verifica si el correo ya está registrado
        if (userRepository.existsByEmail(email)) {
            return false; // El correo ya está en uso
        }

        // Crea un nuevo objeto de usuario y establece los valores
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(password); // Almacena la contraseña encriptada

        userRepository.save(newUser);

        return true; // Usuario creado con éxito
    }
}
