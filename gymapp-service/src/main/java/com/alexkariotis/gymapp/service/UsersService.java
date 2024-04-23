package com.alexkariotis.gymapp.service;

import com.alexkariotis.gymapp.domain.entity.Users;
import com.alexkariotis.gymapp.domain.repository.UsersRepository;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public Try<List<Users>> getAll() {
        return Try.of(usersRepository::findAll);
    }

    public Try<Users> create(final Users user){
        user.setId(UUID.randomUUID());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return Try.of(() -> usersRepository.save(user));
    }

    public Try<Users> update(final Users user){
        user.setUpdatedAt(LocalDateTime.now());
        return Try.of(() -> usersRepository.save(user));
    }

    public Try<Users> findUserByEmail(final String email){
        return Try.of(() -> Option.ofOptional(usersRepository.findByEmail(email))
                .getOrElseThrow(() -> new RuntimeException("User with email " + email + " not found")));
    }

    public Try<Users> findUserById(final UUID id){
        return Try.of(() -> Option.ofOptional(usersRepository.findById(id))
                .getOrElseThrow(() -> new RuntimeException("User with id " + id + " not found")));
    }

    public Try<Void> delete(final UUID id){
        return Try.run(() -> usersRepository.deleteById(id));
    }



}
