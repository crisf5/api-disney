package com.disney.api.auth.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    @Email(message = "Username must be an email")
    private String username;

    @Size(min = 8, max = 64)
    private String password;
}


