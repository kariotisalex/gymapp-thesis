package com.alexkariotis.gymapp.api;


import com.alexkariotis.gymapp.domain.entity.Users;
import com.alexkariotis.gymapp.dto.UsersRequestDto;
import com.alexkariotis.gymapp.mapper.UsersMapper;
import com.alexkariotis.gymapp.service.UsersService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {


    private final UsersService usersService;

    private final UsersMapper usersMapper;


    @GetMapping
    public ResponseEntity<Users> getUser() {
        return ResponseEntity.ok(new Users());
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UsersRequestDto usersRequestDto) {
        return usersService.findUserByEmail(usersRequestDto.getEmail())
                .recoverWith(ignored -> Try.of(() -> usersMapper.toUsers(usersRequestDto)))
                .flatMap(user -> usersService.createNewUser(user))
                .map(ResponseEntity::ok)
                .onFailure(ex -> log.error("UsersController : createUser : "))
                .getOrElseThrow((ex) -> new RuntimeException("Failed to create new User" + ex.getMessage()));



    }
}
