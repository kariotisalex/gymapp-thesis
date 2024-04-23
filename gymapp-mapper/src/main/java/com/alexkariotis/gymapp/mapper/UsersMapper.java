package com.alexkariotis.gymapp.mapper;


import com.alexkariotis.gymapp.domain.entity.Users;
import com.alexkariotis.gymapp.dto.user.UsersRequestDto;
import com.alexkariotis.gymapp.dto.user.UsersResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UsersMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Users toUsers(UsersRequestDto user);

    UsersResponseDto toUsersResponseDto(Users user);
}
