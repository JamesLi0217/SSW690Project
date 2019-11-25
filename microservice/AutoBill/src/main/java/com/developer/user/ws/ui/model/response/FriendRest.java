package com.developer.user.ws.ui.model.response;

import java.util.List;

import com.developer.user.ws.io.entity.UserEntity;

public class FriendRest {
	
	private long Id;
	private int userId;
	private int confirmStateId;
	private int friendId;
	private List<UserEntity> userList;
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
	public int getConfirmStateId() {
		return confirmStateId;
	}
	public void setConfirmStateId(int confirmStateId) {
		this.confirmStateId = confirmStateId;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public List<UserEntity> getUserList() {
		return userList;
	}
	public void setUserList(List<UserEntity> userList) {
		this.userList = userList;
	}
	
}
