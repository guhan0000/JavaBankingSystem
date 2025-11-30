package com.obs;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class UserManagement {
	@Autowired
	DataSource dataSource;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	 void createUser(String userEmail,String userName, String userPwd) {
		Object arr[]= {userName,userEmail,userPwd};
		String sql="INSERT INTO users (full_name,email,password) VALUES (?,?,?)";
		jdbcTemplate.update(sql,arr);

	}
	 User getUser(String userEmail) {
		// TODO Auto-generated method stub
		 String sql="SELECT * FROM users WHERE email=?";
		 return  jdbcTemplate.queryForObject(sql,new Object[] {userEmail},
				   (rs,rNum)->{
					   User user=new User();
					   user.setUserFullName(rs.getString("full_name"));
					   user.setUserEmail(rs.getString("email"));
					   user.setUserPwd(rs.getString("password"));
					   return user;
				   });
		 
		 

	}
	
	
	

}
