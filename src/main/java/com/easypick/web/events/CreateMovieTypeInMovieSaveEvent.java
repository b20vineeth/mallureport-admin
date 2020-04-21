package com.easypick.web.events;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easypick.admin.entity.DataSetup; 
import com.easypick.admin.vo.MovieVo; 
import com.easypick.framework.utility.commonUtility.EventListener;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.web.events.persistence.EventDao;
import com.google.gson.Gson;

@Component
@EventListener(eventKey = "com.admin.saveMovie", priority = "1")
public class CreateMovieTypeInMovieSaveEvent implements EventImpl {

	@Autowired
	private EventDao dao;

	Gson gson;

	@Override
	public void execute(WatchDogVo watchdog, ResponseVo vo) {

		MovieVo movieVo = (MovieVo) vo.getObject();
		if (Objects.nonNull(movieVo.getMovieType())) {
			String[] movietypes = movieVo.getMovieType().split(",");

			DataSetup dataSetup = null;
			for (String names : movietypes) {
				int values = 0;
				try {
					values = Integer.parseInt(names);
				} catch (Exception e) {

					dataSetup = new DataSetup();
					dataSetup.setCode("t-" + names.replace(" ", "-"));
					dataSetup.setDateName(names); 
					dataSetup.setType("MovieType");
					watchdog.getSessionString().saveOrUpdate(dataSetup);
					dao.updateMovieTypetoMovie(movieVo.getMovieId(), watchdog, dataSetup);

				}
			}

		}
	}

}
