package com.easypick.web.video.persistence;
 
import com.easypick.admin.vo.VideoVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

public interface VideoDao {

	 

	ResponseVo saveVideo(WatchDogVo watchdog, VideoVo vo) throws BussinessException;

	ResponseVo getVideoList(WatchDogVo watchdog, VideoVo vo) throws BussinessException;

	ResponseVo getVideo(WatchDogVo watchdog, VideoVo vo) throws BussinessException;

 

 
 




}
