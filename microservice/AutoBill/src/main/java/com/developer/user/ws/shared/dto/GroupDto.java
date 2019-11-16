package com.developer.user.ws.shared.dto;

import java.io.Serializable;
import java.util.List;

public class GroupDto implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3935195604808045709L;
	
	private long Id; // database id
	private int groupId; // return back to mobile app
	private float amount; 
	private int deleteStateId;
	private int checkStateId;
	private List<UserDto> userList;
	
	
	public List<UserDto> getUser() {
		return userList;
	}
	public void setUserDto(List<UserDto> userDto) {
		this.userList = userDto;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
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
