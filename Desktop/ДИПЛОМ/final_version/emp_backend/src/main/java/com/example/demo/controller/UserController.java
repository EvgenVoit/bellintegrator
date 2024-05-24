//package com.example.demo.controller;
//
//import com.example.demo.model.User;
//import com.example.demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping("/log")
//    public String login(){
//        return "authenticated successfully!";
//    }
//
//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("/addUser")
//    public String addUser(@RequestBody User user){
//        return userService.addUser(user);
//    }
//}
