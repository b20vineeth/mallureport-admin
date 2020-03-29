package com.easypick.admin.admin.query;

public class Query {
	
	/**
	 * USER ENTITY 
	 * 
	 */
	public static final String CHECK_USERNAME_EXIST_IN_USERSETUP = "SELECT user_code  FROM usersetup  "
			+ "WHERE cmpcod= :cmpcod  and user_code = :usercode ";
	public static final String CHECK_MOBILE_EXIST_IN_USERSETUP = "SELECT email  FROM usersetup  "
			+ "WHERE cmpcod= :cmpcod  and mobile = :mobile ";
	public static final String COUNT_LIST_IN_USERSETUP_QUERY = "SELECT count(*) COUNT  FROM USERSETUP WHERE 1=1  AND cmpcod=:cmpcod ";
	
			;
	public static String CHECK_EMAIL_EXIST_IN_USERSETUP="SELECT email  FROM usersetup  "
			+ "WHERE cmpcod= :cmpcod  and email = :email "; 
	public static String  CHECK_LIST_IN_USERSETUP = "SELECT USER_ID  , EMAIL  , USER_CODE , CMPCOD , "
			+ "CREATEDDATE , VALIDFRM , VALIDTO , FIRST_NAME , LAST_NAME,STATUS  FROM USERSETUP WHERE 1=1  AND cmpcod=:cmpcod ";
			

	
	/**
	 * GALLERY ENTITY 
	 * 
	 */
	
	
	public static final Object CHECK_PICURL_EXIST_IN_GALLERYSETUP = "SELECT gallery_id  FROM gallery  "
			+ " WHERE cmpcod= :cmpcod ";
	public static final Object CHECK_LIST_IN_GALLERYSETUP = "SELECT CMPCOD,CREATEDDATE,STATUS,GALLERY_DESC,GALLERY_URL,GALLERY_META,GALLERY_PAGE,"
			+ "GALLERY_TAG,GALLERY_TITLE,GALLERY_THUMB FROM GALLERY WHERE  1=1  AND cmpcod=:cmpcod";
	
	
	
	
	
	
	
	
	public static final String GALLERY_LIST_BY_TAG="SELECT movie.thumbnail,movie.short_desc,movie.movie_code,movie.movie_name ,"
			+ " movie.cin_rel_dat, movie.rate ,lan.`langnam` FROM `movie`  movie inner join langsetup lan on lan.id=movie.cin_lang "
			+ " WHERE movie.status='Y'";
	
	public static final String GALLERY_COUNT_BY_TAG="SELECT  COUNT(*) total FROM `movie`  movie inner join langsetup lan on lan.id=movie.cin_lang "
			+ " WHERE movie.status='Y'";
	public static final String COUNT_PREFIX = " select concat(total,'') total from (";
	public static final String COUNT_SUFFIX = " ) t1";
	
	
	
	

}
