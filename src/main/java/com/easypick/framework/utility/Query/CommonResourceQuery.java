package com.easypick.framework.utility.Query;

public class CommonResourceQuery {

	public static final String FIND_CONGIURATION="SELECT `id`,"
			+ "`module`,`submodule`,`controller`,`validity_from`,`validity_to`,"
			+ "`status` FROM `configuration` config WHERE 1=1 ";
}
