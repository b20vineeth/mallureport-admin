package com.easypick.admin.vo;

import com.easypick.framework.utility.vo.AbstractVo;

public class HomePageSetupVo implements AbstractVo {
	
	private Integer id;
	private String type;
	private String data;
	private String status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
