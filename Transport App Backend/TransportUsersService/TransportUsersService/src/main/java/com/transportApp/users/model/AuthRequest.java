package com.transportApp.users.model;

public class AuthRequest {
	String userName;
	String password;
	public AuthRequest() {
		
	}
	public AuthRequest(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
