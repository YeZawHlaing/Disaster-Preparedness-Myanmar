package com.server.backend.service;

import com.server.backend.entity.User;

import java.util.List;

public interface UserService {
    User createUser (User user );
    List<User> getAllUser();
   // User verifyUser(User user);
}
