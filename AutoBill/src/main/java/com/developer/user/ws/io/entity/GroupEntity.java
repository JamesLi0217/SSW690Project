package com.developer.user.ws.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "groupUserList")
public class GroupEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4535592347457306700L;

	@Id
	@GeneratedValue
	private long Id; // database id

	@Column(name = "group_id")
	private int groupId; // return back to mobile app

	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "amount")
	private float amount;

	@Column(name = "delete_state_id")
	private int deleteStateId;

	@Column(name = "check_state_id")
	private int checkStateId;
	
	@ManyToMany(mappedBy = "groupList")
	private List<UserEntity> userList;
	
	

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
