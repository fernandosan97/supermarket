package com.fernando.sinch.supermarket.repository;

import com.fernando.sinch.supermarket.models.ERole;
import com.fernando.sinch.supermarket.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
