package com.easypick.web.video.persistence;
  
import java.util.List;
import java.util.Objects;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.Video;
import com.easypick.admin.vo.VideoVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo; 

@Repository
public class VideoSqlDao implements VideoDao {

	@Override
	public ResponseVo saveVideo(WatchDogVo watchdog, VideoVo vo) throws BussinessException {
		Video video = Video.populateVideoVo(vo);
		watchdog.getSessionString().saveOrUpdate(video);
		vo.setVideoId(video.getVideoId());
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(vo);
		return responseVo;
	}

	@Override
	public ResponseVo getVideoList(WatchDogVo watchdog, VideoVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();

		sql.append("from Video video where video.status='Y'");
		if (Objects.nonNull(vo.getVideoId()))
			sql.append("and video.videoId=:videoId");
		if (Objects.nonNull(vo.getTag()) && vo.getTag().trim().length() > 0)
			sql.append("and video.tag like '%" + vo.getTag() + "%'");

		if (Objects.nonNull(vo.getTitle()) && vo.getTitle().trim().length() > 0)
			sql.append("and video.title like '%" + vo.getTitle() + "%'");

		ResponseVo responseVo = new ResponseVo();
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		if (Objects.nonNull(vo.getVideoId()))
			query.setParameter("videoId", vo.getVideoId());

		Integer page = Integer.parseInt(Objects.isNull(vo.getPage()) ? "0" : vo.getPage());
		if (page > 0)
			page = page - 1;
		page = page * 25;
		sql.append(" order by video.videoId desc ");

		Page page1 = new Page();
		List<Video> videos = query.setFirstResult(page).setMaxResults(page1.getPerPage()).getResultList();
		responseVo.setObjectList(Video.formateVideoVos(videos));

		responseVo.setResponse(true);
		return responseVo;
	}

	@Override
	public ResponseVo getVideo(WatchDogVo watchdog, VideoVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();

		sql.append("from Video video where video.status='Y'");
		if (Objects.nonNull(vo.getVideoId()))
			sql.append("and video.videoId=:videoId");

		ResponseVo responseVo = new ResponseVo();
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		if (Objects.nonNull(vo.getVideoId()))
			query.setParameter("videoId", vo.getVideoId());

		Video video = (Video) query.getSingleResult();
		responseVo.setObject(Video.formateVideoVo(video));
		responseVo.setResponse(true);
		return responseVo;
	}


	 
	    

}
