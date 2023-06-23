package com.dating.crow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dating.crow.profile.dto.ProfileDto;
import com.dating.crow.profile.model.Profile;
import com.dating.crow.profile.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
	@Autowired
	protected ProfileService profileService;

	@GetMapping("/profiles")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllProfiles() {
		return new ResponseEntity<List<Profile>>(profileService.getProfiles(), HttpStatus.OK);
	}
	
	@PostMapping("/profile")
	@PreAuthorize("hasRole('DATER')")
	public ResponseEntity<?> createProfile(@RequestBody ProfileDto profileDto) {
		return new ResponseEntity<Profile>(profileService.create(profileDto), HttpStatus.OK);
	}
}
