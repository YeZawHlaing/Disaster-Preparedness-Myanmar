package com.server.backend.service.serviceImpl;

import com.server.backend.entity.User;
import com.server.backend.repository.UserRepo;
import com.server.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImp implements UserService {
    @Autowired
    private final UserRepo userRepo;

    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
//    private TokenService tokenService;
//    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(16);

    @Override
    public User createUser(User user) {

        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}
