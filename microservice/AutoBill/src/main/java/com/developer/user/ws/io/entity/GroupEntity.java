package com.developer.user.ws.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "group_user_list")
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
	
//	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//	@JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "group_id", referencedColumnName="group_id"),
//	inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName="user_id"))
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<UserEntity> userList;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<UserEntity> getUserList() {
		return userList;
	}

	public void setUserList(List<UserEntity> userList) {
		this.userList = userList;
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
