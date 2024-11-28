package com.lawdocgen.lawdocgen.repositories;

import com.lawdocgen.lawdocgen.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}