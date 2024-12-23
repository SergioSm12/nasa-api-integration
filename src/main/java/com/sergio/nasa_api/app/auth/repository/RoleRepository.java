package com.sergio.nasa_api.app.auth.repository;

import com.sergio.nasa_api.app.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(Role.RoleEnum name);
}
