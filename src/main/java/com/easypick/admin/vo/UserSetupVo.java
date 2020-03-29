package com.easypick.admin.vo;

import java.util.Date;

import com.easypick.framework.utility.vo.AbstractVo;

public class UserSetupVo  implements AbstractVo{

	public static final String UPDATE = "update";
	public static final String LIST = "list";
	public static final String SAVE = "save";
	public static final String CREATE = "create";
	public static final String DELETE = "delete";
	public static final String DEACTIVATE = "deactivate";
	public static final String UPLOAD_EXCEL="uploadExcel";
	
	private String firstName;
	private String username;
	private String email;
	private String gender;
	private String mob;
	private String password;
	private String lastName;
	private String userId;
	private String validityto;
	private String status;
	private String validityFrom;
	private Integer page; 
	private String createDate;
	private String companyCode;
	
	private String confirmPassword;
	
	
	private Date  createDateFromat;
	private Date validitytoFromat; 
	private Date validityFromFromat;
	 
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public Date getCreateDateFromat() {
		return createDateFromat;
	}
	public void setCreateDateFromat(Date createDateFromat) {
		this.createDateFromat = createDateFromat;
	}
	public Date getValiditytoFromat() {
		return validitytoFromat;
	}
	public void setValiditytoFromat(Date validitytoFromat) {
		this.validitytoFromat = validitytoFromat;
	}
	public Date getValidityFromFromat() {
		return validityFromFromat;
	}
	public void setValidityFromFromat(Date validityFromFromat) {
		this.validityFromFromat = validityFromFromat;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getValidityto() {
		return validityto;
	}
	public void setValidityto(String validityto) {
		this.validityto = validityto;
	}
	public String getValidityFrom() {
		return validityFrom;
	}
	public void setValidityFrom(String validityFrom) {
		this.validityFrom = validityFrom;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}

	
	
	
	
}
