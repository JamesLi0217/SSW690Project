package com.developer.user.ws.ui.model.request;

import java.util.List;

public class UserDetailsRequestModel {

	public List<GroupRequestModel> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<GroupRequestModel> groupList) {
		this.groupList = groupList;
	}
	private int userId;
	private String userName;
	private int userStartDate;
	private String userPassword;
	private String userEmail;
	private List<GroupRequestModel> groupList;
	
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
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
}
