package com.easypick.admin.vo;
 

import org.springframework.web.multipart.MultipartFile;
 
public class UploadedFile {
    private static final long serialVersionUID = 1L;
    private MultipartFile  files;
    private Integer x1;
    private Integer y1;
    private Integer w;
    private Integer h;
    private String wh;
    private String movid;
    
    
    
     
	public String getMovid() {
		return movid;
	}
	public void setMovid(String movid) {
		this.movid = movid;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public MultipartFile getFiles() {
		return files;
	}
	public void setFiles(MultipartFile files) {
		this.files = files;
	}
	public Integer getX1() {
		return x1;
	}
	public void setX1(Integer x1) {
		this.x1 = x1;
	}
	public Integer getY1() {
		return y1;
	}
	public void setY1(Integer y1) {
		this.y1 = y1;
	}
	public Integer getW() {
		return w;
	}
	public void setW(Integer w) {
		this.w = w;
	}
	public Integer getH() {
		return h;
	}
	public void setH(Integer h) {
		this.h = h;
	}
	 
	
	
}