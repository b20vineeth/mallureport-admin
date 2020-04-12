package com.easypick.web.slideshow.bussinesscontroller;
    
import com.easypick.admin.vo.SlideShowVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo; 

public interface SlideShowBussinessInterface {
 
 

	ResponseVo saveSlideShowVo(SlideShowVo slideShowVo) throws BussinessException;

	ResponseVo getSlideShow(SlideShowVo slideShowVo) throws BussinessException;

	ResponseVo getAllSlideshow(SlideShowVo show) throws BussinessException;
	 
	

}
