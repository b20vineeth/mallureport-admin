package com.easypick.web.category.bussinesscontroller;
  
import com.easypick.admin.vo.CategoryVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo; 

public interface CategoryBussinessInterface {

	 
	ResponseVo getCategory( CategoryVo vo) throws BussinessException;

	ResponseVo getCategoryList(CategoryVo vo)  throws BussinessException;

	ResponseVo saveCategory(CategoryVo vo)  throws BussinessException;

	

}
