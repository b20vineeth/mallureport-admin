package com.easypick.web.category.persistence;
 
import com.easypick.admin.vo.CategoryVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface CategoryDao {
 

	ResponseVo saveCategory(WatchDogVo watchdog, CategoryVo vo)  throws BussinessException;

	ResponseVo getCategory(WatchDogVo watchdog, CategoryVo vo)  throws BussinessException;

	ResponseVo getCategoryList(WatchDogVo watchdog, CategoryVo vo)  throws BussinessException;

	 



}
