package com.developer.user.ws.io.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "friend_list")
public class FriendsEntity {

	@Id
	@GeneratedValue
	private long Id; // database id
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "friend_id")
	private int friendId;
	
	@Column(name = "comfirm_state_id")
	private int confirmStateId;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//	@JoinTable(name = "user_friend", joinColumns = @JoinColumn(name = "friend_id", referencedColumnName="friend_id"),
//	inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName="user_id"))
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

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public int getConfirmStateId() {
		return confirmStateId;
	}

	public void setConfirmStateId(int confirmStateId) {
		this.confirmStateId = confirmStateId;
	}

	public List<UserEntity> getUserList() {
		return userList;
	}

	public void setUserList(List<UserEntity> userList) {
		this.userList = userList;
	}
}
