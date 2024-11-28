package com.lawdocgen.lawdocgen.repositories;

import com.lawdocgen.lawdocgen.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}