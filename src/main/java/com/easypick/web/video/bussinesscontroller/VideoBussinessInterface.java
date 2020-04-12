package com.easypick.web.video.bussinesscontroller;
   
import com.easypick.admin.vo.VideoVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo; 

public interface VideoBussinessInterface {

 

	ResponseVo saveVideoVo(VideoVo vo) throws BussinessException;

	ResponseVo getVideoList(VideoVo vo) throws BussinessException;

	ResponseVo getVideo(VideoVo videoVo) throws BussinessException;

}
