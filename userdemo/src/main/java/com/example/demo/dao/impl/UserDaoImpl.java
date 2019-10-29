package com.example.demo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;

@Repository
public class UserDaoImpl implements UserDao
{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void createUser(User user)
	{
		String CREATE_USER_SQL = "INSERT INTO users(user_name,user_password, user_email,  user_start_date) VALUES (?,?,?,?)";
		int update = jdbcTemplate.update(CREATE_USER_SQL, user.getUserName(), user.getPassword(), user.getUserEmail(),
				1995 - 7 - 11);
		if (update == 1)
		{
			System.out.println("User is create..");
		}
	}

	@Override
	public User getUser(int user_id) {
		// TODO Auto-generated method stub
		String GET_USER_SQL = "select * from user where user_id=?";
//		int update = jdbcTemplate.update(GET_USER_SQL, user_id);
		
		return jdbcTemplate.queryForObject(GET_USER_SQL, new UserRowMapper(), user_id);
	}
	class UserRowMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("user_id"));
		user.setUserName(rs.getString("user_name"));
		user.setPassword(rs.getString("user_password"));
		return user;
		}
	}
	
	
	

//	@Override
//	public User getID(int user_id)
//	{
//		// TODO Auto-generated method stub
//		
//		return null;
//	}
//
//	@Override
//	public User getUserName(String user_name)
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User getPassword(String user_password)
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User getUserEmail(String user_email)
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}

}
