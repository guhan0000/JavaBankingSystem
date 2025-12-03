package com.obs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

//@Component
public class AccountManagement {
	
	@Autowired
	DataSource dataSource;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	Account account;
	public AccountManagement(Account account) {
		this.account=account;
	}
	
	 Account createAccount(String acFullName,String acEmail,double acBalance,String acPin) {
//		Account account=new Account();
		Object arr[]= {acFullName,acEmail,acBalance,acPin};
		String sql="INSERT INTO accounts (full_name,email,balance,security_pin) VALUES (?,?,?,?)";
		long accNo = jdbcTemplate.queryForObject(
				"SELECT LAST_INSERT_ID()", Long.class
			);

		int update = jdbcTemplate.update(sql,arr);
		if(update==1){
			System.out.println("Account Created Successfully");
			account.setAccountBalance(acBalance);
			account.setFullName(acFullName);
			account.setEmail(acEmail);
			account.setAccountPin(acPin);
			account.setAccountNumber(accNo);
			return account;
		}
		else {
			System.out.println("Account Not Created");
			return null;
		}
	}
	  Double getBalance(long accountNumber) {
		// TODO Auto-generated method stub
		 String sql="SELECT balance FROM accounts WHERE account_number=?";
		return jdbcTemplate.queryForObject(sql,Double.class,accountNumber);

	}
	 
	  Account depositMoney(double depositAmount, long accNumber) {
		// TODO Auto-generated method stub
		
		 Object arr[]= {depositAmount,accNumber};
		 String sql="UPDATE accounts SET balance=balance+? WHERE account_number=?";
		 int update = jdbcTemplate.update(sql,arr);
		 if(update==1) {
			 Double balance = getBalance(accNumber);
//			 Account account=new Account();
			 account.setAccountBalance(balance);
			 System.out.println("Rs. "+depositAmount+" deposited successfully");
			 return account;
		 }
		 else {
			 System.out.println("Rs. "+depositAmount+" not deposited");
			 return null;
		 }
		 

	}
	   Account transferMoney(long accNumber,double transferAmount) {
//		   Double balance = getBalance(accNumber);
		   String sql="UPDATE accounts SET balance=balance+? WHERE account_number=?";
		   int update = jdbcTemplate.update(sql,transferAmount,accNumber);
		   if(update==1) {
			   System.out.println("Rs. "+transferAmount+" transferred sucessfully to "+accNumber);
			   
			   account.setAccountBalance(getBalance(accNumber));
			   return account;
		   }
		   else {
			   System.out.println("Transaction Failed");
			   return null;
		   }
		
		

	}
}
