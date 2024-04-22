package com.alexkariotis.gymapp.dto;

import com.alexkariotis.gymapp.common.UsersType;
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
    private UsersType userType;

}
