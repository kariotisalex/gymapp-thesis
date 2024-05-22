package com.alexkariotis.gymapp.api;


import com.alexkariotis.gymapp.dto.user.UsersRequestDto;
import com.alexkariotis.gymapp.dto.user.UsersResponseDto;
import com.alexkariotis.gymapp.mapper.UsersMapper;
import com.alexkariotis.gymapp.service.UsersService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {


    private final UsersService usersService;

    private final UsersMapper usersMapper;


    @GetMapping
    public ResponseEntity<List<UsersResponseDto>> getAllUser(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "1") int size
    ) {
        return usersService.getAll(page, size)
                .map(users ->  users.stream()
                                    .map(usersMapper::toUsersResponseDto)
                                    .collect(Collectors.toList()))
                .map(ResponseEntity::ok)
                .getOrElseThrow((ex) -> new RuntimeException("Something went wrong"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDto> getUsersById(@PathVariable("id") UUID userId) {
        return usersService.findUserById(userId)
                .map(usersMapper::toUsersResponseDto)
                .map(ResponseEntity::ok)
                .getOrElseThrow((ex) -> new RuntimeException("There is no User with id: " + userId
                        +" error " + ex.getMessage()));
    }

    @PostMapping
    public ResponseEntity<UsersResponseDto> createUser(@RequestBody UsersRequestDto usersRequestDto) {
        return Try.of(() -> usersMapper.toUsers(usersRequestDto))
                .flatMap(usersService::create)
                .map(usersMapper::toUsersResponseDto)
                .map(ResponseEntity::ok)
                .onFailure(ex -> log.error("UsersController : createUser : "))
                .getOrElseThrow((ex) -> new RuntimeException("Failed to create new User" + ex.getMessage()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID userId) {
        return usersService.findUserById(userId)
                .flatMap(users -> usersService.delete(userId))
                .map(ResponseEntity::ok)
                .getOrElseThrow((ex) -> new RuntimeException("Something went wrong" + ex.getMessage()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsersResponseDto> updateUser(@PathVariable("id") UUID userId,
                                                       @RequestBody UsersRequestDto usersRequestDto) {
        return usersService.findUserById(userId)
                .flatMap(usersService::update)
                .map(usersMapper::toUsersResponseDto)
                .map(ResponseEntity::ok)
                .onFailure(ex -> log.error("UsersController : createUser : ", ex))
                .getOrElseThrow((ex) -> new RuntimeException("Failed to create new User" + ex.getMessage()));
    }
}
