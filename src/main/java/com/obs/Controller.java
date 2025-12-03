package com.obs;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Controller {
	static Scanner scanner;
	static {
		scanner=new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		UserManagement userManagement = context.getBean("userManagement",UserManagement.class);
//		userManagement.createUser("Guhan", "guhan@outlook.com", "guhan@123");
//		User user = userManagement.getUser("guhan@outlook.com");
//		System.out.println(user.getUserEmail());
		AccountManagement accountManagement=context.getBean("accountManagement",AccountManagement.class);
		
//		accountManagement.createAccount("Guhan M", "guhan@outlook.com", 1000, "1234");
//		accountManagement.depositMoney(50000, 500001);
		
//		accountManagement.depositMoney(9000, account.getAccountNumber());
//		Account account = accountManagement.createAccount("Arthur M", "arthur@outlook.com", 5000, "6969");
//		accountManagement.depositMoney(5000, account.getAccountNumber());
//		Account transferMoney = accountManagement.transferMoney(500001, 5000);
//		System.out.println(transferMoney.getAccountBalance());
		int input;
		do {
			System.out.println("1. Create Account");
			System.out.println("2. Login");
			System.out.println("3. View Balance");
			System.out.println("4. Deposit Money");
			System.out.println("5. Transfer Money");
			System.out.println("6. Exit");
			System.out.println("Enter input");
			input=scanner.nextInt();
			switch (input) {
			case 1: {
				System.out.println("Account Creation");
				System.out.println("Enter Full Name");
				String fullName=scanner.next();
				System.out.println("Enter email");
				String email=scanner.next();
				System.out.println("Enter password");
				String password=scanner.next();
				
				System.out.println(userManagement.createUser(email, fullName, password));
				
				
				break;
			}
			case 2:{
				System.out.println("User Login");
				System.out.println("Enter email");
				String email = scanner.next();
				System.out.println("Enter Password");
				String password=scanner.next();
				userManagement.userLogin(email, password);
				break;
				
				
				
			}
			case 6:{
				System.exit(0);
			}
			default:
				System.out.println("Invalid Input");
				break;
			}
			
		}while(true);
		
		
		
		
		
	}
	

}
