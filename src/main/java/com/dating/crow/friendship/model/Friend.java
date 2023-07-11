package com.dating.crow.friendship.model;

import java.util.UUID;
import com.dating.crow.profile.model.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Table(name="t_friends")
@Entity
public class Friend {
	@Id
	private UUID id;
	@ManyToOne
    @JoinColumn(name="onwer_id", nullable=false)
	private Profile owner;
	@ManyToOne
    @JoinColumn(name="friend_id", nullable=false)
	private Profile friend;
	@Enumerated(EnumType.STRING)
	private EFriendRequestStatus status;
	@Transient
	private String ownerId;
	@Transient
	private String friendId;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Profile getOwner() {
		return owner;
	}
	public void setOwner(Profile owner) {
		this.owner = owner;
	}
	public Profile getFriend() {
		return friend;
	}
	public void setFriend(Profile friend) {
		this.friend = friend;
	}
	public EFriendRequestStatus getStatus() {
		return status;
	}
	public void setStatus(EFriendRequestStatus status) {
		this.status = status;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	
	
}
