package com.example.estonianpizzaaBE.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.estonianpizzaaBE.model.ERole;
import com.example.estonianpizzaaBE.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    static Optional<Role> findByName(ERole name) {
        return null;
    }
}
