package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.model.User;


public interface UserDao {
	public abstract void createUser(User user);

	public abstract User getUser(int user_id);

	public abstract String getUserEmail(int user_id);

	public abstract String getUserName(int user_id);

	public abstract Integer getUserID(String user_name);
	
	public abstract boolean addFriend(int user1_id, int user2_id);
	
	public abstract boolean confirmFriend(int user1_id, int user2_id);
	
	public abstract boolean needConfirmFriend(int user1_id, int user2_id);
	
	public abstract boolean denyFriend(int user1_id, int user2_id);
	
//	public abstract void uploadPicture();

	public abstract ArrayList<Integer> findAllFriends(int user_id);
}
