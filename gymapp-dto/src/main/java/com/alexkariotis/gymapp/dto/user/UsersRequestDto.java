package com.alexkariotis.gymapp.dto.user;

import com.alexkariotis.gymapp.common.Role;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsersRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate birthdate;
    private String address;
    private Role role;

}
