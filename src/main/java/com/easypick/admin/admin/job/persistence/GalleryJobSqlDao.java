package com.easypick.admin.admin.job.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import com.easypick.admin.vo.GalleryVo;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Component
public class GalleryJobSqlDao implements GalleryDao {

	private String filterType;
	private String language;
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
	public Map<String, List<? extends AbstractVo>> getActressDetails(WatchDogVo watchdog) {
		this.filterType = "F"; 
		return profileDetails(watchdog);
		
		
	}
	@Override
	public Map<String, List<? extends AbstractVo>> getActorDetails(WatchDogVo watchdog) {
		this.filterType = "F"; 
		return profileDetails(watchdog);
		
	}
	
	
	
	
	
	private Map<String, List<? extends AbstractVo>> movieDetails() {
		Map<String, List<? extends AbstractVo>> map = new HashMap<>();
		for (String lang : StringUitity.getLanguage()) {
			List<String> ids = findMovieDetails();
			String sql = constructGalleryMovieQuery(ids);
			List<GalleryVo> vos = excuteQuery(sql);
			map.put(lang, vos);

		}
		return map;
	}
	
	
	

	private List<GalleryVo> excuteQuery(String sql) {
		Query query = watchdog.getSessionString().createSQLQuery(sql);
		List<Object[]> items = query.setFirstResult(0).setMaxResults(this.resultSize).getResultList();
		List<GalleryVo> vos = new ArrayList<>();

		if (items.size() > 0) {
			GalleryVo.populateGalleryVo(items, vos);

		}
		return vos;
	}

	private String constructGalleryMovieQuery(List<String> ids) {
		Integer size = ids.size();
		int i = 0;
		StringBuilder sql = new StringBuilder();
		for (String id : ids) {
			i++;
			sql.append(
					" SELECT  id  galleryId ,'" + id + "' movieid FROM gallery where movie_tag like '%#" + id + "#%' ");
			if (i != size) {
				sql.append(" UNION ");
			}
		}
		String query = "select gallery.tag,gallery.thumbnail1,"
				+ "gallery.title,gallery.url ,gallery.movieid  from gallery " + " inner join ( " + sql.toString()
				+ " ) gallery1 on gallery.id=gallery1.galleryId";
		return query;
	}

	private List<String> findMovieDetails() {
		StringBuilder sql = new StringBuilder();
		sql.append("Select movie.movieId from Movie movie  where movie.status='Y' ");
		if ("U".equals(this.filterType)) {
			sql.append(" and movie.cin_rel_dat>:date");
		} else {
			sql.append(" and movie.cin_rel_dat<=:date");
		}
		sql.append(" and langsetup.langcod=:langCode ");
		sql.append(
				" order by case when movie.priority_flag = 'Y' then 2 when movie.priority_flag = 'N' then 2   else 1 end ,"
						+ " movie.cin_rel_dat desc");
		Query query = watchdog.getSessionString().createSQLQuery(sql.toString());
		query.setDate("date", StringUitity.removeTime(new Date()));

		if (!"P".equals(this.filterType))
			query.setParameter("langCode", this.language);
		List<Object[]> object = query.setFirstResult(0).setMaxResults(this.resultSize).getResultList();

		List<String> vos = new ArrayList<>();
		if (object.size() > 0) {
			for (Object[] items : object) {
				vos.add("#" + items.toString() + "#");
			}

		}
		return vos;
	}

	
	
	


	private String constructGalleryProfileQuery(List<String> ids) {
		Integer size = ids.size();
		int i = 0;
		StringBuilder sql = new StringBuilder();
		for (String id : ids) {
			i++;
			sql.append(
					" SELECT  id  galleryId ,'" + id + "' profileId FROM gallery where profile_tag like '%#" + id + "#%' ");
			if (i != size) {
				sql.append(" UNION ");
			}
		}
		String query = "select gallery.tag,gallery.thumbnail1,"
				+ "gallery.title,gallery.url ,gallery.movieid  from gallery " + " inner join ( " + sql.toString()
				+ " ) gallery1 on gallery.id=gallery1.galleryId";
		return query;
	}

	private List<String> findProfileDetails() {
		 
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT   profileid   from  profile where  status ='Y' " );
		sql.append(" and  gender=:gender");
		 sql.append( " order by  updated_on  desc");
		Query query = watchdog.getSessionString().createSQLQuery(sql.toString());
		 query.setParameter("gender",this.filterType);
		List<Object[]> object = query.setFirstResult(0).setMaxResults(this.resultSize).getResultList();

		List<String> vos = new ArrayList<>();
		if (object.size() > 0) {
			for (Object[] items : object) {
				vos.add("#" + items.toString() + "#");
			}

		}
		return vos;
	}

	

	private Map<String, List<? extends AbstractVo>> profileDetails(WatchDogVo watchdog) {
		this.watchdog = watchdog;
		Map<String, List<? extends AbstractVo>> map = new HashMap<>();
		for (String lang : StringUitity.getLanguage()) {
			List<String> ids = findProfileDetails();
			String sql = constructGalleryProfileQuery(ids);
			List<GalleryVo> vos = excuteQuery(sql);
			map.put(lang, vos);
		}
		return map;
	}

	

}
