package com.developer.user.ws.ui.model.request;


public class UserDetailsRequestModel {

	private int userId;
	private String userName;
	private int userStartDate;
	private String userPassword;
	private String userEmail;
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
