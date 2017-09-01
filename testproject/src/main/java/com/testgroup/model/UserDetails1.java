package com.testgroup.model;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class UserDetails1 implements Serializable
{
	
	
	@Id
	private String userid;
	private String password;
	private String userrole;
	private boolean enabled;
	
	@OneToOne(mappedBy="userDetails")
	private Customer customer;



	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUserrole() {
		return userrole;
	}


	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}