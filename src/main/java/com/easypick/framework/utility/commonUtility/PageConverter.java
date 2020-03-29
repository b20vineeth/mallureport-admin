package com.easypick.framework.utility.commonUtility;

import java.util.Map;
import java.util.Objects;

import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.Page;

public class PageConverter {

	public Page validatePageRequest(String keyElement) throws BussinessException {
		String[] key = keyElement.split("-");
		Page page = new Page();
		if (key.length > 0) {
			if (key[0].equalsIgnoreCase("page")) {
				page = validatePageNumber(key);
			} else {
				page.setPages(false);
			}
		} else {
			page.setPages(false);
		}

		return page;
	}

	private Page validatePageNumber(String[] key) {
		Page page = new Page();
		if (key.length == 2) {
			try {
				page.setCurrentPage(Integer.parseInt(key[1]));
				page.setPages(true);
			} catch (Exception e) {
				page.setPages(false);
			}
		} else {
			page.setPages(false);
		}
		return page;
	}

	public Integer totalPage(Integer rowCount) {
		Integer rem = rowCount % Page.MAX_RESULT;
		int total = (rowCount - rem) / Page.MAX_RESULT;
		if (rem > 0) {
			total += 1;
		}

		return total;
	}

	public String getStartPage(Integer page) {
		if (Objects.isNull(page)) {
			return " 0 ,"+Page.MAX_RESULT;
		} else {
			if(page>0)
				page=page-1;
			return page * Page.MAX_RESULT + " , "+Page.MAX_RESULT;
		}

	}

}
