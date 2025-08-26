package com.ty.userService.service.impl;

import com.ty.userService.dto.UserRequestDto;
import com.ty.userService.dto.UserResponseDto;
import com.ty.userService.repository.UserRepository;
import com.ty.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {


        return null;
    }

    @Override
    public UserResponseDto getById(String id) {
        return null;
    }
}
