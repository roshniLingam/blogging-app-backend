package com.bloggingapp.bloggingapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.bloggingapp.bloggingapp.entity.User;
import com.bloggingapp.bloggingapp.exception.ResourceNotFoundException;
import com.bloggingapp.bloggingapp.repository.UserRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user from database by username
        User user = userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email", 0));
        return user;
    }
    
}
