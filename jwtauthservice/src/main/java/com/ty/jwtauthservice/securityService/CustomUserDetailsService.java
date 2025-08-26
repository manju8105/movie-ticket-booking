package com.ty.jwtauthservice.securityService;

import com.ty.jwtauthservice.entity.User;
import com.ty.jwtauthservice.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

         User user =authRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User with email "+email+" is not found"));
        System.out.println(user);

        return new CustomerUserDetails(user);
    }
}
