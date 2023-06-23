package com.dating.crow.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dating.crow.profile.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
