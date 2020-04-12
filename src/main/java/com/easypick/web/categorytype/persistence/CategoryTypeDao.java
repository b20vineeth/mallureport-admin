package com.easypick.web.categorytype.persistence;
 
import com.easypick.admin.vo.CategoryTypeVo; 
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface CategoryTypeDao {

	 

	ResponseVo saveCategoryType(WatchDogVo watchdog, CategoryTypeVo vo) throws BussinessException;

	ResponseVo getCategoryTypeList(WatchDogVo watchdog, CategoryTypeVo vo) throws BussinessException;

	ResponseVo getCategoryType(WatchDogVo watchdog, CategoryTypeVo vo) throws BussinessException;

	


}
