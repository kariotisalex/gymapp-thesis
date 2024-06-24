package com.alexkariotis.gymapp.dto.user;

import com.alexkariotis.gymapp.common.Role;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsersCreateDto {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private String birthdate;
    private String address;
    private Role role;

}
