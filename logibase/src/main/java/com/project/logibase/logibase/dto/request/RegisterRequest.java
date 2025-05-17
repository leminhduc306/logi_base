package com.project.logibase.logibase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String address;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
