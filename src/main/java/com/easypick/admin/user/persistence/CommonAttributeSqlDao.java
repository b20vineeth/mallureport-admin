package com.easypick.admin.user.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.SQLQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.Category;
import com.easypick.admin.entity.CategoryType;
import com.easypick.admin.entity.CineGallery;
import com.easypick.admin.entity.Gallery;
import com.easypick.admin.entity.Language;
import com.easypick.admin.entity.Movie;
import com.easypick.admin.entity.MovieGallery;
import com.easypick.admin.entity.MovieReview;
import com.easypick.admin.entity.Profile;
import com.easypick.admin.entity.Settings;
import com.easypick.admin.entity.SlideShow;
import com.easypick.admin.entity.Video;
import com.easypick.admin.vo.CategoryTypeVo;
import com.easypick.admin.vo.CategoryVo;
import com.easypick.admin.vo.CinemaGalleryVo;
import com.easypick.admin.vo.DataVo;
import com.easypick.admin.vo.GalleryVo;
import com.easypick.admin.vo.LanguageVo;
import com.easypick.admin.vo.MovieReviewVo;
import com.easypick.admin.vo.MovieVo;
import com.easypick.admin.vo.ProfileVo;
import com.easypick.admin.vo.SettingsVo;
import com.easypick.admin.vo.SlideShowVo;
import com.easypick.admin.vo.VideoVo;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.mapper.MovieItemMapper;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.google.gson.Gson;

@Repository
public class CommonAttributeSqlDao implements CommonAttributeDao {

	 
	 

	

	
	
	

	
	

	

	
	
	

	
	

	

	


	
	
	


	

	
	
	

	private void saveMovieGalleryDetails(WatchDogVo watchdog, Map<String, String> key, List<Object[]> gallerys,
			String type) {
		for (Object[] items : gallerys) {
			try {

				MovieGallery movievo = new MovieGallery();
				movievo.setThumb1(items[0].toString());
				movievo.setTitle(items[1].toString());
				movievo.setTag(items[2].toString());
				String[] tags=items[3].toString().replaceAll("#", "").split(",");
				String url=null;
				for(String tag:tags)
				{
					url=key.get(tag);
				}
				movievo.setUrl(url);
				movievo.setType(type);
				watchdog.getSessionString().saveOrUpdate(movievo); 

			} catch (Exception e) {

			}

		}
	}
	
	
	
	
	@Override
	public ResponseVo saveGalleryDetails(WatchDogVo watchdog, MovieVo vo) throws BussinessException {

		StringBuilder sql = new StringBuilder();
		ResponseVo responseVo = new ResponseVo();
		Map<String,String> key=new HashMap<>();
		try {
			sql.append(
					"SELECT GROUP_CONCAT( CONCAT(movie.movieId ,' ',`movie_code`))  FROM movie movie where movie.status='Y' ");
			if ("U".equals(vo.getFilterType())) {
				sql.append(" and movie.cin_rel_dat>:date");
			} else {
				sql.append(" and movie.cin_rel_dat<=:date");
			}
			sql.append(" order by  case when movie.priority_flag = 'Y' then 2 when movie.priority_flag = 'N'"
					+ " then 2   else 1 end  , movie.cin_rel_dat asc");
			SQLQuery query = watchdog.getSessionString().createSQLQuery(sql.toString());
			query.setParameter("date", StringUitity.removeTime(new Date()));
			String objects = null; 
			try {
				objects = (String) (query).uniqueResult();

			} catch (Exception e) {

			}
			if (Objects.nonNull(objects)) {
				sql = new StringBuilder();
				String[] movieIds = objects.split(",");
				for (String ids : movieIds) {
					String[] idn = ids.trim().split(" ");
					sql.append(" gallery.movie_tag like '%");
					sql.append("#" + idn[0] + "#%' or ");
					key.put(idn[0], idn[1]);
				}

			}
			StringBuilder sql1 = new StringBuilder();
			sql1.append("SELECT `thumbnail1`, `title`,`tag`,movie_tag FROM `gallery` WHERE STATUS='Y' ");
			sql1.append("and (");
			sql1.append(sql.toString().trim().replaceAll("or$", ""));
			sql1.append(") order by  id  desc");
			query = watchdog.getSessionString().createSQLQuery(sql1.toString());
			List<Object[]> gallerys = (List<Object[]>) ((org.hibernate.Query) query).list(); 
			
			String type="Movie-"+vo.getFilterType();
			deleteMovieGallery(watchdog, type);
			
			if(Objects.nonNull(gallerys))
			{
				saveMovieGalleryDetails(watchdog, key, gallerys, type);
				  
			}
			responseVo.setResponse(true);

		} catch (Exception e) {
			System.out.print(e.toString());
		}
		return responseVo;

	}
	@Override
	public ResponseVo getdata(WatchDogVo watchdog, SettingsVo vo) throws BussinessException {

		ResponseVo responseVo = new ResponseVo();
		if ("cinema".equals(vo.getType())) {
			StringBuilder sql = new StringBuilder();
			sql.append("select movie.movieId ,movie.movieName from Movie movie where movie.status='Y' ");

			if (Objects.nonNull(vo.getTag())) {
				sql.append(" and movie.movieName like '%" + vo.getTag() + "%'");
			}
			Query query = watchdog.getSessionString().createQuery(sql.toString());

			List<Object[]> object = query.setFirstResult(0).setMaxResults(10).getResultList();
			List<DataVo> vos = new ArrayList<>();
			DataVo dataVo = null;

			if (object.size() > 0) {
				DataVo.populateDataVo(object, vos); 
				responseVo.setObjectList(vos);

			}

		} else if ("profile".equals(vo.getType())) {

			StringBuilder sql = new StringBuilder();
			sql.append("select profile.profileId ,profile.profileName from Profile profile where profile.status='Y' "
					+ " and profile.profileName like '%:profileName%'");
			Query query = watchdog.getSessionString().createQuery(sql.toString());
			query.setParameter("profileName", vo.getTag());

			List<Profile> profiles = query.setFirstResult(0).setMaxResults(10).getResultList();
			responseVo.setObjectList(Profile.formateProfileVos(profiles));

		}
		return responseVo;
	}

	private void deleteMovieGallery(WatchDogVo watchdog, String type) {
		Query query1 = watchdog.getSessionString().createQuery("delete MovieGallery where type=:type");
		  query1.setParameter("type", type);
		  int result = query1.executeUpdate();
	}

}
