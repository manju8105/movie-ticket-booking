package com.ty.userService.dto;

import com.ty.userService.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "UserRequestDTO", description = "Request body for user creation")
public class UserRequestDto {

    @NotBlank(message = "First Name is required")
    private String firstName;
    @NotBlank(message = "Last Name is required")
    private String lastName;

    @NotBlank(message = "Date of birth is required.")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of birth must be in the format YYYY-MM-DD.")
    private String dateOfBirth;

    @Email(message = "Email should be valid.")
    private String email;

    @Pattern(regexp = "\\d{6}", message = "Pin code must be 6 digits.")
    private String pinCode;

    @NotBlank(message = "Mobile number is required.")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits.")
    private String phone;

}
