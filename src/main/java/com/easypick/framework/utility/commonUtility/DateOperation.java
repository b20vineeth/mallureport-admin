package com.easypick.framework.utility.commonUtility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateOperation {
	
	 
	public Date formateDate(Object items) throws ParseException
	{
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		 
		 Date date = formatter.parse(items.toString());
		 return date;
	}
	public Date formateDate(Object items,String dateFormat) throws ParseException
	{
		 SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		 
		 Date date = formatter.parse(items.toString());
		 return date;
	}

	public String convertDateToString(Date date)
	{
		 SimpleDateFormat sdfDestination = new SimpleDateFormat("DD/MM/yyyy");
		 if(date!=null)
		 {
			return sdfDestination.format(date);
		 }
		    
		return "";
		
	}
	public String convertDateToString(Date date,String formate)
	{
		 SimpleDateFormat sdfDestination = new SimpleDateFormat(formate);
		 if(date!=null)
		 {
			return sdfDestination.format(date);
		 }
		    
		return "";
		
	}
	 
}
