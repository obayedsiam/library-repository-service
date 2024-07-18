package com.example.library.dto;

import com.example.library.enums.RoleEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class RegisterUserDto {

    private String email;
    private String password;
    private String fullName;
    private RoleEnum roleEnum;
}
