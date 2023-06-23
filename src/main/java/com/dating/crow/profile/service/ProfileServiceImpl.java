package com.dating.crow.profile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dating.crow.profile.dto.ProfileDto;
import com.dating.crow.profile.model.EGender;
import com.dating.crow.profile.model.Profile;
import com.dating.crow.profile.repository.ProfileRepository;
import com.dating.crow.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Profile create(ProfileDto profileDto) {
		Profile profile=new Profile();
		profile.setName(profileDto.getName());
		profile.setOtherName(profileDto.getOtherName());
		profile.setGender(EGender.valueOf(profileDto.getGender()));
		profile.setAddress(profileDto.getAddress());
		profile.setUser(userRepository.findByUsername(profileDto.getUsername()).get());
		return profileRepository.save(profile);
	}

	@Override
	public List<Profile> getProfiles() {
		return profileRepository.findAll();
	}
	
	
	
}
