package com.dating.crow.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dating.crow.user.model.ERole;
import com.dating.crow.user.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	  Optional<Role> findByName(ERole name);
}
