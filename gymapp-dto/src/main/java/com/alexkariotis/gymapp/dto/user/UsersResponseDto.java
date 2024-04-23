package com.alexkariotis.gymapp.dto.user;

import com.alexkariotis.gymapp.common.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponseDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate birthdate;
    private String address;
    private Role role;

}
