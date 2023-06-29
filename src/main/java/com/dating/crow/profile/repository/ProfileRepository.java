package com.dating.crow.profile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dating.crow.profile.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
	@Query(value = "SELECT p.* FROM t_profiles p,t_users u WHERE p.user_id = u.id and u.username=:username",nativeQuery = true)
	Optional<Profile> findProfileByUsername(@Param("username") String username);

}
