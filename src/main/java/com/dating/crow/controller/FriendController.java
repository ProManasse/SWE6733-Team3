package com.dating.crow.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dating.crow.friendship.model.EFriendRequestStatus;
import com.dating.crow.friendship.model.Friend;
import com.dating.crow.friendship.repo.FriendRepository;
import com.dating.crow.profile.model.Profile;
import com.dating.crow.profile.repository.ProfileRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/friend")
public class FriendController {
	@Autowired
	private FriendRepository friendRepository;
	
	@Autowired 
	private ProfileRepository profileRepository;
	
	@PostMapping("/request")
	public ResponseEntity<?> sendFriendRequest(@RequestBody Friend friend){
		Profile owner=profileRepository.findById(Long.parseLong(friend.getOwnerId())).get();
		Profile newFriend=profileRepository.findById(Long.parseLong(friend.getFriendId())).get();
		friend.setFriend(newFriend);
		friend.setOwner(owner);
		friend.setStatus(EFriendRequestStatus.WAITING);
		friend.setId(UUID.randomUUID());
		return new ResponseEntity<Friend>(friendRepository.save(friend),HttpStatus.OK);
	}
	
	@PostMapping("/friends")
	public ResponseEntity<?> getMyFriends(@RequestBody Friend friend){
		Profile owner=profileRepository.findById(Long.parseLong(friend.getOwnerId())).get();
		return new ResponseEntity<List<Friend>>(owner.getFriends(),HttpStatus.OK);
	}
	
}
