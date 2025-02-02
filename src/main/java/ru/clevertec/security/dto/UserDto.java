package ru.clevertec.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private int age;
    private String username;
    private String password;

}
