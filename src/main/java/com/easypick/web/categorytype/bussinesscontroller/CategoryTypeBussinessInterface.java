package com.easypick.web.categorytype.bussinesscontroller;
 
import com.easypick.admin.vo.CategoryTypeVo; 
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo; 

public interface CategoryTypeBussinessInterface {

	ResponseVo saveCategoryType(CategoryTypeVo vo)throws BussinessException;

	ResponseVo getCategoryTypeList(CategoryTypeVo vo) throws BussinessException;

	ResponseVo getCategoryType(CategoryTypeVo vo) throws BussinessException;

	

}
