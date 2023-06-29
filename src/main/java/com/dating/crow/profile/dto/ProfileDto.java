package com.dating.crow.profile.dto;

public class ProfileDto {
	private String name;
	private String otherName;
	private String gender;
	private String address;
	private String dob;
	private String username;
	private String phoneNo;
	private String adventure;
	private String skill;
	private String behavior;
	
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAdventure() {
		return adventure;
	}
	public void setAdventure(String adventure) {
		this.adventure = adventure;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getBehavior() {
		return behavior;
	}
	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "ProfileDto [name=" + name + ", otherName=" + otherName + ", gender=" + gender + ", address=" + address
				+ ", dob=" + dob + ", username=" + username + ", phoneNo=" + phoneNo + ", adventure=" + adventure
				+ ", skill=" + skill + ", behavior=" + behavior + "]";
	}
	
	
}
