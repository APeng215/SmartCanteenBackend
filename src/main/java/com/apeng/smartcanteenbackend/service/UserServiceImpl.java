package com.apeng.smartcanteenbackend.service;

import com.apeng.smartcanteenbackend.entity.Authorities;
import com.apeng.smartcanteenbackend.entity.Users;
import com.apeng.smartcanteenbackend.repository.AuthoritiesRepository;
import com.apeng.smartcanteenbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @Override
    public void addUser(String username, String password, String role) {
        userRepository.save(new Users(username, new BCryptPasswordEncoder().encode(password)));
        authoritiesRepository.save(new Authorities(username, role));
    }

}
