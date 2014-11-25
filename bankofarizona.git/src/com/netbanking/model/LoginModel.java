package com.netbanking.model;

import javax.validation.constraints.Size;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class LoginModel {
	
	@Size(min=6, max=20, message="Please Eneter a valid UserName") 
	private String username;
	@Size(min=6, max=20, message="Please Eneter a valid Password")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
