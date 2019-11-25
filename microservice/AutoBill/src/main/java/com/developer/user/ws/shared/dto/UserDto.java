package com.developer.user.ws.shared.dto;

import java.io.Serializable;
import java.util.List;

import com.developer.user.ws.io.entity.GroupEntity;

// user data transfer object
public class UserDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4772531166811017951L;
	
	private long Id; // database id
	private int userId; // return back to mobile app
	private String userName = "lpz";
	private int userStartDate = 111;
	private String userEmail = "pppp";
	private String userPassword = "ssss";
	private String encryptedPassword = "false"; // encrypted password stored in database
	private String emailVerificationToken;
	private boolean emailVerificationStatus = false;
	private List<GroupDto> groupList;
	
	public List<GroupDto> getAllGroups() {
		return groupList;
	}
	public void setAllGroups(List<GroupDto> allGroups) {
		this.groupList = allGroups;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserStartDate() {
		return userStartDate;
	}
	public void setUserStartDate(int userStartDate) {
		this.userStartDate = userStartDate;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	
	
	
	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}


	
}
