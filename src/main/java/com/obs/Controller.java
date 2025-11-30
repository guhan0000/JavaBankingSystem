package com.obs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Controller {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		UserManagement userManagement = context.getBean("userManagement",UserManagement.class);
//		userManagement.createUser("Guhan", "guhan@outlook.com", "guhan@123");
		User user = userManagement.getUser("guhan@outlook.com");
		System.out.println(user.getUserEmail());
		
	}

}
