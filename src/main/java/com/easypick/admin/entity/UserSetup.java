package com.easypick.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
 

@Entity
@Table(name = "usersetup" , uniqueConstraints = { @UniqueConstraint(columnNames = { "username" ,"useremail"}) })



public class UserSetup  {


	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue 
	@Column(name = "user_id")
	private Integer userId ;
	@Column(name = "username", length=120)
	private String username;
	@Column(name = "useremail", length=120 ,nullable=false)
	private String email;
	@Column(name = "frst_nam", length=120)
	private String Firstname;
	@Column(name = "last_nam", length=120)
	private String lastname;

	@Column(name = "paswd", length=120 ,nullable=false)
	private String password;

	@Column(name = "status", length=2 ,nullable=false)
	private String status="Y";

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	


}
