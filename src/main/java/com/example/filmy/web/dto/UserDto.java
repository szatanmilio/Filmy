package com.example.filmy.web.dto;

public class UserDto {
	private String email;
	private String password;
	private String aPassword;

	public UserDto() {
	}

	public UserDto(String email, String password, String aPassword) {
		this.email = email;
		this.password = password;
		this.aPassword = aPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getaPassword() {
		return aPassword;
	}

	public void setaPassword(String aPassword) {
		this.aPassword = aPassword;
	}
}
