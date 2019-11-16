package com.developer.user.ws.ui.model.request;

import java.util.List;

import com.developer.user.ws.shared.dto.UserDto;

public class GroupRequestModel {
	
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
	private float amount; 
	private int deleteStateId;
	private int checkStateId;
	
}
