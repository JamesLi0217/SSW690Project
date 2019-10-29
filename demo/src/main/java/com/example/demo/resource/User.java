package com.example.demo.resource;

public class User
{
	// fields
	public String name;

	public String password;

	// constructor
	public User() {
		
	}
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	
	
	
	
	
	
	
	
	
	// get name
	public String getName()
	{
		return name;
	}
	
	
	// set name
	public void setName(String name)
	{
		this.name = name;
	}
	
	// get password
	public String getPassword()
	{
		return password;
	}
	
	// set password
	public void setPassword(String passWord)
	{
		this.password = passWord;
	}

	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
