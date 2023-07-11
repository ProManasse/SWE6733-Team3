package com.dating.crow.profile.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dating.crow.profile.dto.ProfileDto;
import com.dating.crow.profile.model.EAdventure;
import com.dating.crow.profile.model.EBehavior;
import com.dating.crow.profile.model.EGender;
import com.dating.crow.profile.model.ESkill;
import com.dating.crow.profile.model.Profile;
import com.dating.crow.profile.repository.ProfileRepository;
import com.dating.crow.user.dto.MessageResponse;
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
		System.out.println("Tests are passing here");
		Profile profile=new Profile();
		profile.setName(profileDto.getName());
		profile.setOtherName(profileDto.getOtherName());
		profile.setGender(EGender.valueOf(profileDto.getGender()));
		profile.setAddress(profileDto.getAddress());
		profile.setGender(EGender.valueOf(profileDto.getGender()));
		profile.setAdventure(EAdventure.valueOf(profileDto.getAdventure()));
		profile.setBehavior(EBehavior.valueOf(profileDto.getBehavior()));
		profile.setSkill(ESkill.valueOf(profileDto.getSkill()));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDate = LocalDate.parse(profileDto.getDob(), formatter);
		profile.setDob(localDate);
		profile.setUser(userRepository.findByUsername(profileDto.getUsername()).get());
		return profileRepository.save(profile);
	}

	@Override
	public List<Profile> getProfiles() {
		return profileRepository.findAll();
	}

	@Override
	public MessageResponse deleteProfile(String username) {
		if(!profileRepository.findProfileByUsername(username).isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "You don't have profile.");
		}
		Profile p=profileRepository.findProfileByUsername(username).get();
		 profileRepository.delete(p);
		return new MessageResponse("Deleted Successfully");
	}
	
	
	
}
