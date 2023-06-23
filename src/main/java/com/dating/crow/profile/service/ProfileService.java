package com.dating.crow.profile.service;

import java.util.List;
import java.util.Optional;

import com.dating.crow.profile.dto.ProfileDto;
import com.dating.crow.profile.model.Profile;

public interface ProfileService {
	Profile create(ProfileDto profileDto);
	List<Profile> getProfiles();
}
