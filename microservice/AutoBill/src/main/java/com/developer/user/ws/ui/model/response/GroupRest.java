package com.developer.user.ws.ui.model.response;

import java.util.List;

public class GroupRest {
	
//	private long Id; // database id
	private int groupId; // return back to mobile app
	private float amount; 
	private int deleteStateId;
	private int checkStateId;
	private List<UserRest> userList;
	
	
	public List<UserRest> getUserList() {
		return userList;
	}
	public void setUserList(List<UserRest> userList) {
		this.userList = userList;
	}
//	public long getId() {
//		return Id;
//	}
//	public void setId(long id) {
//		Id = id;
//	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getDeleteStateId() {
		return deleteStateId;
	}
	public void setDeleteStateId(int deleteStateId) {
		this.deleteStateId = deleteStateId;
	}
	public int getCheckStateId() {
		return checkStateId;
	}
	public void setCheckStateId(int checkStateId) {
		this.checkStateId = checkStateId;
	}
	
	

}
