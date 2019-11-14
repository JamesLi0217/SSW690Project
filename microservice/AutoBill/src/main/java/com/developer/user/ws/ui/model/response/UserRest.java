package com.developer.user.ws.ui.model.response;

import java.util.List;

import com.developer.user.ws.io.entity.GroupEntity;

public class UserRest {
	
	private int userId;
	private String userName;
	private int userStartDate;
//	public String user_password;  request model can contain password, response can't contain password
	private String userEmail;
	
	private List<GroupEntity> groupList;
	
	public List<GroupEntity> getAllGroups() {
		return groupList;
	}
	public void setAllGroups(List<GroupEntity> allGroups) {
		this.groupList = allGroups;
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
	
	
	

}
