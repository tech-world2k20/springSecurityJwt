package com.example.springSecurity.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequest {

    private String name;
    private String password;
}
