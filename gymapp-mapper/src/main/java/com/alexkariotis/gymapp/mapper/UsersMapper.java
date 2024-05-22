package com.alexkariotis.gymapp.mapper;


import com.alexkariotis.gymapp.domain.entity.Users;
import com.alexkariotis.gymapp.dto.user.UsersRequestDto;
import com.alexkariotis.gymapp.dto.user.UsersResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper
public interface UsersMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "birthdate", target = "birthdate", qualifiedByName = "transformation")
    Users toUsers(UsersRequestDto user);

    UsersResponseDto toUsersResponseDto(Users user);

    @Named("transformation")
    public static LocalDate Transformation(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);

    }
}
