package com.ty.jwtauthservice.service.impl;

import com.ty.jwtauthservice.dto.LoginUserDto;
import com.ty.jwtauthservice.dto.RegisterUserRequestDto;
import com.ty.jwtauthservice.entity.User;
import com.ty.jwtauthservice.repository.AuthRepository;
import com.ty.jwtauthservice.security.JwtUtil;
import com.ty.jwtauthservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.http.auth.InvalidCredentialsException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;


    @Override
    public void register(RegisterUserRequestDto registerUserRequestDto) {

        if(authRepository.findByEmail(registerUserRequestDto.getEmail()).isPresent())
        {
            throw new RuntimeException("Email Already exists");
        }

        User user=modelMapper.map(registerUserRequestDto,User.class);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        authRepository.save(user);

    }

    @Override
    public String login(LoginUserDto loginUserDto) throws InvalidCredentialsException {

       User user=authRepository.findByEmail(loginUserDto.getEmail()).orElseThrow(()->new UsernameNotFoundException("User with email "+loginUserDto.getEmail()+" is not found"));

//        User user=modelMapper.map(loginUserDto,User.class);


        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(),loginUserDto.getPassword()));

        if(!authentication.isAuthenticated()){
            throw new InvalidCredentialsException("password is incorrect");
        }
        return jwtUtil.generateToken(user);
    }
}
