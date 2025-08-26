package com.ty.jwtauthservice.controller;

import com.ty.jwtauthservice.dto.ApiResponse;
import com.ty.jwtauthservice.dto.LoginUserDto;
import com.ty.jwtauthservice.dto.RegisterUserRequestDto;
import com.ty.jwtauthservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/booking")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody RegisterUserRequestDto registerUserRequestDto)
    {
        authService.register(registerUserRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED,"User is created Successfully, Still password want to reset",null,null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginUserDto loginUserDto) throws InvalidCredentialsException
    {
        String token=authService.login(loginUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK,"User is Authenticated",token,null));
    }
}