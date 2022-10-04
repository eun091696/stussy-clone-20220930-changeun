package com.stussy.stussyclon20220930changeun.dto;

import lombok.Data;

@Data
public class RegisterReqDto {
    private String lastName;
    private String firstName;
    private String email;
    private String password;
}
