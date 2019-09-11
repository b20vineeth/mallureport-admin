package com.easypick.framework.utility.vo;

public class Page { 

	Integer totalPage=0;
	Integer currentPage=0;
	Integer totalResult=0;
	boolean pages=true;
	Integer perPage=25;

	public static final int MAX_RESULT = 25;
	
	
	
	public Integer getPerPage() {
		return perPage;
	}
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	public boolean isPages() {
		return pages;
	}
	public void setPages(boolean pages) {
		this.pages = pages;
	}
	 
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(Integer totalResult) {
		this.totalResult = totalResult;
	}



}
