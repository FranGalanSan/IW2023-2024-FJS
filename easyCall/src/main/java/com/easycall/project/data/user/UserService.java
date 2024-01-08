package com.easycall.project.data.user;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    public UserRepository userRepository;

    public List<User> getUser() {
        return userRepository.findAll();
    }
}
