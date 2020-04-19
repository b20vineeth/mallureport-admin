package com.easypick.web.events;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easypick.admin.entity.Movie;
import com.easypick.admin.vo.GalleryVo;
import com.easypick.framework.utility.commonUtility.EventListener;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;
import com.easypick.web.events.persistence.EventDao;
import com.google.gson.Gson;

@Component
@EventListener(eventKey = "com.admin.saveGallery", priority = "1")
public class CreateMovieDetailsGallerySaveEvent implements EventImpl {

	@Autowired
	private EventDao dao;

	Gson gson;

	@Override
	public void execute(WatchDogVo watchdog, ResponseVo vo) {

		GalleryVo galleryVo = (GalleryVo) vo.getObject();
		if (Objects.nonNull(galleryVo.getMovie())) {
			String[] films = galleryVo.getMovie().split(",");

			Movie movie = null;
			for (String names : films) {
				int values = 0;
				try {
					values = Integer.parseInt(names);
				} catch (Exception e) {
					
					movie = new Movie();
					movie.setMovieName(names);
					movie.setMovieCode("t-"+names.replace(" ", "-"));
					movie.setCast("#"+galleryVo.getGalleryId()+"#");
					watchdog.getSessionString().saveOrUpdate(movie);
					dao.updateProfileGallery(galleryVo.getGalleryId(), watchdog, movie);
					
				}
			}

		}
	}

}
