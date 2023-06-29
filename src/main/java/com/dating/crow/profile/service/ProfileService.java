package com.dating.crow.profile.service;

import java.util.List;
import java.util.Optional;

import com.dating.crow.profile.dto.ProfileDto;
import com.dating.crow.profile.model.Profile;
import com.dating.crow.user.dto.MessageResponse;

public interface ProfileService {
	Profile create(ProfileDto profileDto);
	List<Profile> getProfiles();
	MessageResponse deleteProfile(String profile);
}
