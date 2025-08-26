package com.ty.jwtauthservice.service;

import com.ty.jwtauthservice.dto.LoginUserDto;
import com.ty.jwtauthservice.dto.RegisterUserRequestDto;
import com.ty.jwtauthservice.entity.User;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    void register(RegisterUserRequestDto authRequestDto);

    String login(LoginUserDto loginUserDto) throws InvalidCredentialsException;
}
