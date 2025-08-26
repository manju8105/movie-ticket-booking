package com.ty.userService.service;

import com.ty.userService.dto.UserRequestDto;
import com.ty.userService.dto.UserResponseDto;
import com.ty.userService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponseDto registerUser(UserRequestDto userRequestDto);

    UserResponseDto getById(String id);

//    void loginUser(UserRequestDto userRequestDto);
}
