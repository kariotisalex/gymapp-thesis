package com.alexkariotis.gymapp.api;

import com.alexkariotis.gymapp.dto.security.AuthenticationRequest;
import com.alexkariotis.gymapp.dto.security.AuthenticationResponse;
import com.alexkariotis.gymapp.dto.user.UsersCreateDto;
import com.alexkariotis.gymapp.mapper.UsersCreateMapper;
import com.alexkariotis.gymapp.service.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UsersCreateMapper usersCreateMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UsersCreateDto usersCreateDto
    ) {
        return ResponseEntity.ok(AuthenticationResponse
                .builder()
                .token(service.register(usersCreateMapper.toUsers(usersCreateDto)))
                .build());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        String authenticate = service.authenticate(username, password);

        return ResponseEntity.ok(AuthenticationResponse
                .builder()
                .token(authenticate)
                .build());
    }
}
