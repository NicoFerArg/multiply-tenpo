package com.springboot.multiply.services;

import com.springboot.multiply.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    public String addUser(User user);

//    public User loadUserByUsername(String username);
}
