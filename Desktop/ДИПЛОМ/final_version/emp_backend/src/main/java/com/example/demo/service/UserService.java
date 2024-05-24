//package com.example.demo.service;
//
//import com.example.demo.adminRepository.UserRepository;
//import com.example.demo.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @Autowired
//    private PasswordEncoder encoder;
//
//    public String addUser(User user) {
//        user.setPassword(encoder.encode(user.getPassword()));
//        userRepo.save(user);
//        return "User was registered successfully!";
//    }
//}
