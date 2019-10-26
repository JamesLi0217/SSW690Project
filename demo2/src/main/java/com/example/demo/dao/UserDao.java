package com.example.demo.dao;

import com.example.demo.model.User;


public interface UserDao {
	public abstract void createUser(User user);
	public abstract User getID(int user_id);
	public abstract User getUserName(String user_name);
	public abstract User getPassword(String user_password);
	public abstract User getUserEmail(String user_email);

}
