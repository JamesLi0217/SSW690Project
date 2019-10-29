package com.example.demo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;

@Repository
//public class UserDaoImpl extends JdbcDaoSupport implements UserDao
public class UserDaoImpl implements UserDao
{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate( (javax.sql.DataSource) dataSource);
    }

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
		System.out.println("success");
		return jdbcTemplate.queryForObject(GET_USER_SQL, new UserRowMapper(), user_id);
	}
	class UserRowMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("user_id"));
		user.setUserName(rs.getString("user_name"));
		user.setPassword(rs.getString("user_password"));
		System.out.println(user.user_name);
		return user;
		}
	}
	@Override
	public String getUserName(int user_id) {
		// TODO Auto-generated method stub
		String GET_USER_NAME_SQL = "select user_name from users where user_id=?";
		System.out.println("get user name success");
		return jdbcTemplate.queryForObject(GET_USER_NAME_SQL, String.class, user_id);
//		return this.getJdbcTemplate().queryForObject(GET_USER_NAME_SQL, String.class, user_id);
//		return null;
	}

	@Override
	public String getUserEmail(int user_id) {
		// TODO Auto-generated method stub
		String GET_USER_NAME_SQL = "select user_email from users where user_id=?";
		System.out.println("get user email success");
		return jdbcTemplate.queryForObject(GET_USER_NAME_SQL, String.class, user_id);
	}


}
