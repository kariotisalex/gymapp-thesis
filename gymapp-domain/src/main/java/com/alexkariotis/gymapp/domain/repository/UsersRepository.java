package com.alexkariotis.gymapp.domain.repository;

import com.alexkariotis.gymapp.domain.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {

    Optional<Users> findByEmail(final String email);

    Page<Users> findAll(Pageable pageable);


}
