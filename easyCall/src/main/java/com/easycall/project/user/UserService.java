package com.easycall.project.user;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    public UserRepository userRepository;

    public List<User> getPersons() {
        return userRepository.findAll();
    }

    public void savePerson(User person) {
        userRepository.save(person);
    }
}
