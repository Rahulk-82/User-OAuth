package com.auth.user.security.services;

import com.auth.user.models.User;
import com.auth.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserServiceDetials implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username is email
        Optional<User> optionalUser=userRepository.findByEmail(username);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("This user is not present");
        }
        User user=optionalUser.get();
        return new CustomUserDetails(user);
    }
}
