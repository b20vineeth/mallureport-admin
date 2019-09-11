package com.easypick.framework.utility.commonUtility;

import java.util.Map;

import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.Page;

public class PageConverter {

	public Page validatePageRequest(String keyElement) throws BussinessException {
		String[] key=keyElement.split("-");
		Page page =new Page();
		if(key.length>0)
		{
			if(key[0].equalsIgnoreCase("page"))
			{
				page=validatePageNumber(key);
			}
			else
			{
				page.setPages(false);
			}
		}
		else
		{
			page.setPages(false);
		}
		 
		return page;
	}

	private Page validatePageNumber(String[] key)  {
		Page page=new Page();
		if(key.length==2)
		{
			try
			{
			  page.setCurrentPage(Integer.parseInt(key[1]));
			  page.setPages(true);
			}
			catch(Exception e)
			{
				page.setPages(false);
			}
		}
		else
		{
			page.setPages(false);
		}
		 return page;
	}

}
