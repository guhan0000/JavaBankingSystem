package com.obs;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
//@Component
public class UserManagement {
	@Autowired
	DataSource dataSource;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	User user;
	public UserManagement(User user){
		this.user=user;
		
	}
	
	 User createUser(String userEmail,String userName, String userPwd) {
		Object arr[]= {userName,userEmail,userPwd};
		String sql="INSERT INTO users (full_name,email,password) VALUES (?,?,?)";
		int update = jdbcTemplate.update(sql,arr);
		if(update==1) {
			System.out.println(userName+" User created Successfully");
			user.setUserFullName(userName);
			user.setUserEmail(userEmail);
			user.setUserPwd(userPwd);
			return user;
			}
		else {
			return null;
		}

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
				   });}
	  void userLogin(String email,String password) {
		// TODO Auto-generated method stub
		  Object arr[]= {email,password};
		  String sql="SELECT email FROM users WHERE email=? AND password=?";
		  String result = jdbcTemplate.queryForObject(sql, String.class,arr);
//		   if(!result.equals("")){
//			   System.out.println(result+" Logged in Sucessfully");
//		 }
//		   else {
//			System.out.println("User "+email+" Not Found");
//		}
		 System.out.println(result);
	}
	
	
	

}
