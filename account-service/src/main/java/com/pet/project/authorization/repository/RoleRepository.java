package com.pet.project.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pet.project.authorization.domain.entity.ERole;
import com.pet.project.authorization.domain.entity.Roles;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

  Optional<Roles> findByName(ERole name);
}
