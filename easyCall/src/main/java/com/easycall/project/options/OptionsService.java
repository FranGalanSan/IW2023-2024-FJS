package com.easycall.project.options;

import com.easycall.project.data.user.User;
import com.easycall.project.data.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OptionsService {

    private final OptionsRepository optionsRepository;
    private final UserRepository userRepository;

    @Autowired
    public OptionsService(OptionsRepository optionsRepository, UserRepository userRepository) {
        this.optionsRepository = optionsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createOrUpdateOptions(User user, Options options) {
        user.setOptions(options);
        userRepository.save(user);
    }
}