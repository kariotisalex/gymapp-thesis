package com.alexkariotis.gymapp.service.security;


import com.alexkariotis.gymapp.domain.entity.Users;
import com.alexkariotis.gymapp.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(final Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        usersRepository.save(users);
        return jwtService.generateToken(users);
    }

    public String authenticate(final String username, final String password) {
        System.out.println("Got here!");
        try {
            System.out.println("Authenticating user: " + username);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            );
            System.out.println("Authentication successful for user: " + username);
        } catch (AuthenticationException e) {
            System.err.println("Authentication failed for user: " + username);
            e.printStackTrace();
            throw e;  // Rethrow the exception or handle it accordingly
        }
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        username,
//                        password
//                )
//        );

        Users users = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return jwtService.generateToken(users);
    }
}
