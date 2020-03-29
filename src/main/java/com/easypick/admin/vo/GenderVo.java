package com.easypick.admin.vo;

import com.easypick.framework.utility.vo.AbstractVo;

public class GenderVo implements AbstractVo{
	
	private String genderName;
	private String genderCode;
	
	public String getGenderName() {
		return genderName;
	}
	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	

}
