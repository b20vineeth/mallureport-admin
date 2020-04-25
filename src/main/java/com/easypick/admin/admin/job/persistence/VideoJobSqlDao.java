package com.easypick.admin.admin.job.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.easypick.admin.vo.VideoVo;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Component
public class VideoJobSqlDao implements VideoDao {

	private String filterType; 
	private WatchDogVo watchdog;
	private Integer resultSize = 6;

	@Override
	public Map<String, List<? extends AbstractVo>> getRunningMovies(WatchDogVo watchdog) {
		this.filterType = "R"; 
		this.watchdog = watchdog;
		return movieDetails();
	}
	
	@Override
	public Map<String, List<? extends AbstractVo>> getUpcommingMovies(WatchDogVo watchdog) {
		this.filterType = "U";
		this.watchdog = watchdog;
		return movieDetails();
	}
	 
	 

	@Override
	public Map<String, List<? extends AbstractVo>> getTrendingVideos(WatchDogVo watchdog) {
		this.resultSize = 9;
		this.watchdog = watchdog;
		return trendingVideoDetails();
	}

	
	
	private Map<String, List<? extends AbstractVo>> trendingVideoDetails() {
		Map<String, List<? extends AbstractVo>> map = new HashMap<>();
		String sql = constructVideoQuery();
		List<VideoVo> vos = excuteQuery(sql);
		map.put("ALL", vos);
		return null;
	}

	 

	private String constructVideoQuery() {
		String sql=" select video.tag,video.thumbnail1, video.title,video.url ,video1.videoId  "
				+ "FROM  video  where  tag  like'%trending%' order by  id  desc";
		 return sql;
	}

	private Map<String, List<? extends AbstractVo>> movieDetails() {
		Map<String, List<? extends AbstractVo>> map = new HashMap<>();
		 List<String> ids = findMovieDetails();
			String sql = constructVideoMovieQuery(ids);
			List<VideoVo> vos = excuteQuery(sql);
			map.put("ALL", vos);

		 
		return map;
	}
	
	
	

	private List<VideoVo> excuteQuery(String sql) {
		Query query = watchdog.getSessionString().createSQLQuery(sql);
		List<Object[]> items = query.setFirstResult(0).setMaxResults(this.resultSize).getResultList();
		List<VideoVo> vos = new ArrayList<>();

		if (items.size() > 0) {
			VideoVo.populateVideoVo(items, vos);

		}
		return vos;
	}

	private String constructVideoMovieQuery(List<String> ids) {
		Integer size = ids.size();
		int i = 0;
		StringBuilder sql = new StringBuilder();
		for (String id : ids) {
			i++;
			sql.append(
					" SELECT  id  videoId ,'" + id + "' videoid FROM video where thumbnail1!=null  video_tag like '%#" + id + "#%' ");
			if (i != size) {
				sql.append(" UNION ");
			}
		}
		String query = "select video.tag,video.thumbnail1,"
				+ "video.title,video.url ,video1.videoId  from video " + " inner join ( " + sql.toString()
				+ " ) video1 on video.id=video1.videoId";
		return query;
	}

	private List<String> findMovieDetails() {
		StringBuilder sql = new StringBuilder();
		sql.append("Select movie.movieId from Movie movie  where movie.status='Y' ");
		if ("U".equals(this.filterType)) {
			sql.append(" and movie.cin_rel_dat>:date");
		} else  {
			sql.append(" and movie.cin_rel_dat<=:date");
		}
		 sql.append(
				" order by case when movie.priority_flag = 'Y' then 2 when movie.priority_flag = 'N' then 2   else 1 end ,"
						+ " movie.cin_rel_dat desc");
		Query query = watchdog.getSessionString().createSQLQuery(sql.toString());
		query.setDate("date", StringUitity.removeTime(new Date()));

	 
		List<Object[]> object = query.setFirstResult(0).setMaxResults(this.resultSize).getResultList();

		List<String> vos = new ArrayList<>();
		if (object.size() > 0) {
			for (Object[] items : object) {
				vos.add("#" + items.toString() + "#");
			}

		}
		return vos;
	}

	
	
	

 

	 
	
	

}
