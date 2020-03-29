package com.easypick.framework.utility.vo;

public class Page {

	Integer totalPage = 0;
	Integer currentPage = 0;
	Integer totalResult = 0;
	boolean pages = false;
	Integer perPage = 10;
	Integer firstResult = 0;

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

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

	public void updateTotalPage() {
		Integer rem = totalResult % perPage;
		Integer pageUp = 0;
		if (rem > 0) {
			pageUp = 1;
		}
		Integer totalPage = ((totalResult - rem) / perPage) + pageUp;
		this.totalPage = totalPage;

	}

	public static String getPage(String string) {

		Integer pageNumber = 1;
		try {
			pageNumber = Integer.parseInt(string);
		} catch (Exception e) {
			pageNumber = 1;
		}
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		
		return "limit "+(pageNumber-1)*10 + ", 10";
	}

}
