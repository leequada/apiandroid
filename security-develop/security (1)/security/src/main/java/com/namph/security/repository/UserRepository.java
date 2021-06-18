package com.namph.security.repository;

import com.namph.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUsername(String s);

    Optional<User> findUserByEmail(String email);

    User findUserByName(String name);
}
