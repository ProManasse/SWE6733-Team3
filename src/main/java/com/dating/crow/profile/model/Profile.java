package com.dating.crow.profile.model;

import java.time.LocalDate;
import java.util.List;

import com.dating.crow.friendship.model.Friend;
import com.dating.crow.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_profiles")
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private String otherName;
	@Enumerated(EnumType.STRING)
	private EGender gender;
	private LocalDate dob;
	private String[] photos;
	private String address;
	@Enumerated(EnumType.STRING)
	private EAdventure adventure;
	@Enumerated(EnumType.STRING)
	private ESkill skill;
	@Enumerated(EnumType.STRING)
	private EBehavior behavior;
	@JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	@JsonIgnore
    @OneToMany(mappedBy="owner")
    private List<Friend> friends;
	@JsonIgnore
    @OneToMany(mappedBy="friend")
    private List<Friend> onwers;    

	public Profile() {
		super();
	}
	public Profile(Long id, String name, String otherName, EGender gender, String address, User user) {
		super();
		this.id = id;
		this.name = name;
		this.otherName = otherName;
		this.gender = gender;
		this.address = address;
		this.user = user;
	}
	
	public Profile(String name, String otherName, EGender gender, String address, User user) {
		this.name = name;
		this.otherName = otherName;
		this.gender = gender;
		this.address = address;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOtherName() {
		return otherName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	public EGender getGender() {
		return gender;
	}
	public void setGender(EGender gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String[] getPhotos() {
		return photos;
	}
	public void setPhotos(String[] photos) {
		this.photos = photos;
	}
	public EAdventure getAdventure() {
		return adventure;
	}
	public void setAdventure(EAdventure adventure) {
		this.adventure = adventure;
	}
	public ESkill getSkill() {
		return skill;
	}
	public void setSkill(ESkill skill) {
		this.skill = skill;
	}
	public EBehavior getBehavior() {
		return behavior;
	}
	public void setBehavior(EBehavior behavior) {
		this.behavior = behavior;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public List<Friend> getFriends() {
		return friends;
	}
	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}
	public List<Friend> getOnwers() {
		return onwers;
	}
	public void setOnwers(List<Friend> onwers) {
		this.onwers = onwers;
	}
	
	
}
