package com.dating.crow.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.dating.crow.profile.dto.ProfileDto;
import com.dating.crow.profile.model.Profile;
import com.dating.crow.profile.photo.FileInfo;
import com.dating.crow.profile.photo.FilesStorageService;
import com.dating.crow.profile.photo.ResponseMessage;
import com.dating.crow.profile.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
	@Autowired
	protected ProfileService profileService;

	@Autowired
	protected FilesStorageService storageService;

	@GetMapping("/profiles")
	// @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllProfiles() {
		return new ResponseEntity<List<Profile>>(profileService.getProfiles(), HttpStatus.OK);
	}

	@PostMapping("/profile")
	@PreAuthorize("hasRole('DATER')")
	public ResponseEntity<?> createProfile(@RequestBody ProfileDto profileDto) {
		return new ResponseEntity<Profile>(profileService.create(profileDto), HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("id") String accessCode,
			@RequestParam("files") MultipartFile[] files) {
		String message = "";
		try {
			List<String> fileNames = new ArrayList<>();
			Arrays.asList(files).stream().forEach(file -> {
				storageService.save(accessCode, file);
				fileNames.add(file.getOriginalFilename());
			});
			storageService.updateProfile(accessCode, fileNames);
			message = "Uploaded the files successfully: " + fileNames;
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Fail to upload files!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/files")
	public ResponseEntity<List<FileInfo>> getListFiles() {
		List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(ProfileController.class, "getFile", path.getFileName().toString()).build()
					.toString();

			return new FileInfo(filename, url);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
