package com.developer.user.ws.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 698121435989821113L;

	@Id
	@GeneratedValue
	private long Id;
	@Column(name = "user_id",nullable = false)
	private int userId; // return back to mobile app
	
	@Column(nullable = false, length = 50)
	private String userName = "lpz";
	@Column(nullable = false, length = 20)
	private int userStartDate = 111;
	@Column(nullable = false, length = 100)
	private String userEmail = "pppp";
	@Column(nullable = false, length = 50)
	private String userPassword = "ssss";
	
	@Column(name = "encrypted_password",nullable = false)
	private String encryptedPassword = "fasle"; // encrypted password stored in database
	
	private String emailVerificationToken;
	
	@Column(nullable = false)
	private boolean emailVerificationStatus ;
	
	@ManyToMany(mappedBy = "userList")
	private List<GroupEntity> groupList;

	public List<GroupEntity> getAllGroups() {
		return groupList;
	}

	public void setAllGroups(List<GroupEntity> allGroups) {
		this.groupList = allGroups;
	}

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

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	
	

	

}
