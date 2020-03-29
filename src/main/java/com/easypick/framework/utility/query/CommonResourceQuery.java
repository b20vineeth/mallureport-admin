package com.easypick.framework.utility.query;

public class CommonResourceQuery {
	
	public static final String[] pagelist = new String[]{"about", "contact", "phone"};
 

	public static final String LIST_CINEMA_DETAILS = "SELECT cat.catnam ,cat.catcod,cin.title,cin.shortdesc ,cin.thumbnail "
			+ "FROM cinema cin inner join category cat  on cat.id=cin.catid  inner join langsetup lang on lang.id=cin.lang "
			+ "where lang.status='Y' and cat.status='Y' and cin.status='Y' and lang.langcod=:lang  ";
	public static final String COUNT_CINEMA_DETAILS = "SELECT COUNT(*) result "
			+ "FROM cinema cin inner join category cat  on cat.id=cin.catid  inner join langsetup lang on lang.id=cin.lang "
			+ "where lang.status='Y' and cat.status='Y' and cin.status='Y' and lang.langcod=:lang  ";

	public static final String FIND_CATEGORY_DETAILS = null;


	public static final Object FIND_CONGIURATION = null;


	public static final Object START_FIND_COUNT_ROW_RESULT = null;


	public static final Object END_FIND_COUNT_ROW_RESULT = null;

	 
}
