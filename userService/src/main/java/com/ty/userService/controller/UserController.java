package com.ty.userService.controller;

import com.ty.userService.dto.ApiResponse;
import com.ty.userService.dto.UserRequestDto;
import com.ty.userService.dto.UserResponseDto;
import com.ty.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequestDto)
    {
                UserResponseDto responseDto =userService.registerUser(userRequestDto);

                return ResponseEntity.ok(responseDto);
    }


    /*@GetMapping("/login")
    public ResponseEntity<UserResponseDto> loginUser(@RequestBody UserRequestDto userRequestDto)
    {
        userService.loginUser(userRequestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUserprofile(@PathVariable String id)
    {

        UserResponseDto responseDto=userService.getById(id);

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,"User Detail fetched successfully",responseDto,null));

    }


   /* public ResponseEntity<ApiResponse<UserResponseDto>> userUpdatePatchUpdate(@PathVariable String email,@RequestBody UserRequestDto userRequestDto)
    {
        userService.updateUserDetail(id,);
    }*/


}
