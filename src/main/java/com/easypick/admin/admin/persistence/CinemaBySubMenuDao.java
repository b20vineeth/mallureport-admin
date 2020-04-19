package com.easypick.admin.admin.persistence;
 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
 
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
 
import com.easypick.admin.vo.MovieVo; 
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CinemaBySubMenuDao implements Dao {

	public static final String COUNT_PREFIX = " select concat(total,'') total from (";
	public static final String COUNT_SUFFIX = " ) t1";
	
	@Override
	public ResponseVo execute(WatchDogVo watchDog, ResponseVo vo) throws BussinessException {
		Query query;
		vo=new ResponseVo();
		StringBuilder sql = new StringBuilder();
		String queryString="SELECT mov.movie_code,mov.movie_name,cin_rel_dat,lang.langnam ,gallery.thumb1,"
				+ "mov.rate,mov.certificate,mov.mov_type , mov.short_desc ";
		
		sql.append(" FROM movie mov inner join langsetup lang on lang.id=mov.cin_lang "
				+ "inner join cinegallery gallery on gallery.movid=mov.movieId "
				+ "where mov.status='Y' ");
		Map<String, String> input = watchDog.getInput();
		String title=null;
		
		title=constructQuery(sql, input);
		Page page1 = new Page();
		Integer page = Integer.parseInt(Objects.isNull(input.get("page")) ? "0" : input.get("page"));
		page1.setCurrentPage(page);
		if (page > 0)
			page = page - 1;
		Map<String, List<? extends AbstractVo>> objectMapList = new HashMap<>();
		page = page * page1.getPerPage();
		query = watchDog.getSessionString().createSQLQuery(queryString+sql.toString());
		query.setParameter("date", new Date());
		query.setFirstResult(page);
		query.setMaxResults(page1.getPerPage());
		List<Object[]> objects = (List<Object[]>) (query).list();
		List<MovieVo> movievos=new ArrayList<>();
		MovieVo movie=null;
		if(objects.size()>0)
		{
			for (Object[] items : objects) {
				movie=new MovieVo();
				movie.setMovieCode(items[0].toString());
				movie.setMovieName(items[1].toString());
				try{
					Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(items[2].toString());
					movie.setReleaseDate(new SimpleDateFormat("dd MMMM, yyyy").format(date1));
				}
				catch(Exception e) {
					
				}
				movie.setLanguageName(items[3].toString());
				movie.setMovieRate(items[4].toString());
				movie.setMovieRate(Objects.nonNull(items[5])?items[5].toString():"0");
				movie.setCertificate(Objects.nonNull(items[6])?items[6].toString():" ");
				movie.setMovieType(Objects.nonNull(items[7])?items[7].toString():" ");
				movie.setShortDesc(Objects.nonNull(items[8])?items[8].toString():" ");
				movievos.add(movie);
			}
			if (Objects.isNull(vo.getObjectMapList())) {
				
				vo.setObjectMapList(objectMapList);
			}
			vo.getObjectMapList().put("data", movievos);
		}
		else
		{
			throw new BussinessException("404");
		}
		 
		 
		MovieVo movieDetails=new MovieVo(); 
		movieDetails.setTitle(title);
		movieDetails.setUrl(input.get("submenu"));
		
		queryString="Select count(*) total ";
		query = watchDog.getSessionString().createSQLQuery(COUNT_PREFIX + queryString+sql.toString() + COUNT_SUFFIX);
		query.setParameter("date", new Date());
		Integer rowCount = 0;
		try {
			rowCount = Integer.parseInt((String) query.uniqueResult());
			page1.setTotalResult(rowCount);
			page1.setPerPage(10);
			page1.updateTotalPage();
			watchDog.setPage(page1);
		} catch (Exception e) {

		}
		vo.setPage(page1);
		vo.setResponse(true);
		vo.setObject(movieDetails);
		return vo;

	}

	private String constructQuery(StringBuilder sql, Map<String, String> input) {
		 String title="";
		if(Objects.nonNull(input.get("language")))
		{
			sql.append(" and lang.langnam='"+input.get("language")+"'");
		}
		if("running-movies".equalsIgnoreCase(input.get("submenu")))
		{
			title="Running Movies";
			sql.append(" and mov.cin_rel_dat<=:date order by case when mov.priority_flag = 'Y'"
					+ " then 2 when mov.priority_flag = 'N' then 2   else 1 end, mov.cin_rel_dat asc");
		 }
		else if("upcomming-movies".equalsIgnoreCase(input.get("submenu")))
		{
			sql.append(" and mov.cin_rel_dat>:date order by case when mov.priority_flag = 'Y'"
					+ " then 2 when mov.priority_flag = 'N' then 2   else 1 end, mov.cin_rel_dat asc");
			title="Upcomming Movies";
		
		}
		else if("popular-film".equalsIgnoreCase(input.get("submenu")))
		{
			sql.append(" and mov.rec_flag='Y' and mov.cin_rel_dat>:date  order by case when mov.priority_flag = 'Y'"
					+ " then 2 when mov.priority_flag = 'N' then 2   else 1 end, mov.cin_rel_dat asc");
			title="Popular Movies";
		}
		return title;
	}

	 
}
