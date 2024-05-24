//package com.example.demo.service;
//
//import com.example.demo.adminRepository.UserRepository;
//import com.example.demo.config.MyUserDetails;
//import com.example.demo.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.Optional;
//
//public class UserDetailService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepo;
//    @Override
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepo.findByName(username);
//        return user.map(MyUserDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
//    }
//
//
//}
