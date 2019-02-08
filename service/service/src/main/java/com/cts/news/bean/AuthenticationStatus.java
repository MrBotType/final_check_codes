package com.cts.news.bean;

public class AuthenticationStatus {

	private boolean authenticated;
	private boolean isAdmin;
	private boolean checkEmail;
	private String message;
	private User user; 
	
	public AuthenticationStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isCheckEmail() {
		return checkEmail;
	}

	public void setCheckEmail(boolean checkEmail) {
		this.checkEmail = checkEmail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "AuthenticationStatus [authenticated=" + authenticated + ", isAdmin=" + isAdmin + ", checkEmail="
				+ checkEmail + ", message=" + message + ", user=" + user + "]";
	}

	
}

