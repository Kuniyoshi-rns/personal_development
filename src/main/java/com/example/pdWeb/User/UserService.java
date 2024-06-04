package com.example.pdWeb.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    IUserRepository userRepository;
    @Override
    public User findByUser(LoginForm loginForm) {
        return userRepository.findByUser(loginForm);
    }

    @Override
    public void insert(CreateForm createForm) {
        userRepository.insert(createForm);
    }
}
