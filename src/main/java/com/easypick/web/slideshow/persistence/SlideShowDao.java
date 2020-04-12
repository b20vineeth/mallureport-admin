package com.easypick.web.slideshow.persistence;
   
import com.easypick.admin.vo.SlideShowVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface SlideShowDao {
 

	ResponseVo saveSlideShowVo(WatchDogVo watchdog, SlideShowVo slideShowVo) throws BussinessException;

	ResponseVo getSlideShow(WatchDogVo watchdog, SlideShowVo slideShowVo) throws BussinessException;

	ResponseVo getAllSlideshow(WatchDogVo watchdog, SlideShowVo show) throws BussinessException;

 
}
