package com.example.demo.resource;

import java.util.ArrayList;

public class ReLogImp implements ReLog
{
	private static ArrayList<User> users = new ArrayList<User>();

	// find out if the user in the db, and the password is correct??
	@Override
	public boolean isLogin(String name, String password)
	{
		boolean flag = false;
		for (User u : users)
		{
			if (u.getName().equals(name) && u.getPassword().equals(password))
			{
				flag = true;
				return flag;
			}
		}
		return flag;
	}

	@Override
	public void register(User user)
	{
		// add user into user list
		users.add(user);
	}

}
