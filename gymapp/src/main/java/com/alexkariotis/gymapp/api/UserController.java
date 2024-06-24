package com.alexkariotis.gymapp.api;


import com.alexkariotis.gymapp.dto.user.UsersCreateDto;
import com.alexkariotis.gymapp.dto.user.UsersResponseDto;
import com.alexkariotis.gymapp.dto.user.UsersUpdateDto;
import com.alexkariotis.gymapp.mapper.UsersCreateMapper;
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
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    private final UsersService usersService;

    private final UsersCreateMapper usersCreateMapper;


    @GetMapping
    public ResponseEntity<List<UsersResponseDto>> getAllUser(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "1") int size
    ) {
        return usersService.getAll(page, size)
                .map(users ->  users.stream()
                                    .map(usersCreateMapper::toUsersResponseDto)
                                    .collect(Collectors.toList()))
                .map(ResponseEntity::ok)
                .getOrElseThrow((ex) -> new RuntimeException("Something went wrong"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDto> getUsersById(@PathVariable("id") UUID userId) {
        return usersService.findUserById(userId)
                .map(usersCreateMapper::toUsersResponseDto)
                .map(ResponseEntity::ok)
                .getOrElseThrow((ex) -> new RuntimeException("There is no User with id: " + userId
                        +" error " + ex.getMessage()));
    }


//    Note : Deleted because there is no authentication for a new user without credentials.
//    @PostMapping
//    public ResponseEntity<UsersResponseDto> createUser(@RequestBody UsersCreateDto usersCreateDto) {
//        return Try.of(() -> usersCreateMapper.toUsers(usersCreateDto))
//                .flatMap(usersService::create)
//                .map(usersCreateMapper::toUsersResponseDto)
//                .map(ResponseEntity::ok)
//                .onFailure(ex -> log.error("UsersController : createUser : "))
//                .getOrElseThrow((ex) -> new RuntimeException("Failed to create new User" + ex.getMessage()));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID userId) {
        return usersService.findUserById(userId)
                .flatMap(users -> usersService.delete(userId))
                .map(ResponseEntity::ok)
                .getOrElseThrow((ex) -> new RuntimeException("Something went wrong" + ex.getMessage()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsersResponseDto> updateUser(@PathVariable("id") UUID userId,
                                                       @RequestBody UsersUpdateDto usersUpdateDto) {
        return usersService.findUserById(userId)
                .flatMap(usersService::update)
                .map(usersCreateMapper::toUsersResponseDto)
                .map(ResponseEntity::ok)
                .onFailure(ex -> log.error("UsersController : createUser : ", ex))
                .getOrElseThrow((ex) -> new RuntimeException("Failed to create new User" + ex.getMessage()));
    }
    // todo seperate update other information from username or/and password.
}
