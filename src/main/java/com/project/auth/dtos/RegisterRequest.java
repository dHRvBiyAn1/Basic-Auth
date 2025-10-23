package com.project.auth.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
}
