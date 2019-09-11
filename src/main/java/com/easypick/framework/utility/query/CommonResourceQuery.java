package com.easypick.framework.utility.query;

public class CommonResourceQuery {
	

	public static final String FIND_CONGIURATION="SELECT  config.configuration_id configurationId,config.controller_class controllerClass,config.module module,config.submodule subModule"
			+ " FROM  configuration  config  WHERE config.status='Y'  ";

	public static String START_FIND_COUNT_ROW_RESULT="SELECT CONCAT(COUNT,'') AS COUNT FROM ( ";
	public static String END_FIND_COUNT_ROW_RESULT=" ) T1" ;
	
}
