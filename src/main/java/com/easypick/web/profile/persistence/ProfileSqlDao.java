package com.easypick.web.profile.persistence;
 
import java.util.List; 
import java.util.Objects;

import org.hibernate.SQLQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
 
import com.easypick.admin.entity.Profile; 
import com.easypick.admin.vo.ProfileVo; 
import com.easypick.framework.utility.exception.BussinessException; 
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo; 

@Repository
public class ProfileSqlDao implements ProfileDao {

	@Override
	public ResponseVo profileSave(WatchDogVo watchdog, ProfileVo vo) throws BussinessException {
		Profile profile = Profile.populateProfileVo(vo);
		watchdog.getSessionString().saveOrUpdate(profile);
		vo.setProfileId(profile.getProfileId());
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(vo);
		return responseVo;
	}

	@Override
	public ResponseVo getProfileList(WatchDogVo watchdog, ProfileVo vo) throws BussinessException {

		StringBuilder sql = new StringBuilder();
		sql.append(" from Profile profile where profile.status='Y' ");
		if (Objects.nonNull(vo.getCatName())) {
			sql.append(" and profile.profileName like '%" + vo.getProfileName() + "%'");
		}
		if (Objects.nonNull(vo.getTag()) && vo.getTag().trim().length() > 0) {
			sql.append(" and profile.tag like '%" + vo.getTag() + "%'");
		}
		if (Objects.nonNull(vo.getFilms()) && vo.getFilms().trim().length() > 0) {
			sql.append(" and profile.films like '%" + vo.getFilms() + "%'");
		}
		if (Objects.nonNull(vo.getTag()) && vo.getTag().trim().length() > 0) {
			sql.append(" and profile.tag like '%" + vo.getTag() + "%'");
		}
		if (Objects.nonNull(vo.getGender()) && vo.getGender().trim().length() > 0) {
			sql.append(" and profile.gender = '" + vo.getGender() + "'");
		}

		Integer page = Integer.parseInt(Objects.isNull(vo.getPage()) ? "0" : vo.getPage());
		if (page > 0)
			page = page - 1;
		page = page * 25;
		sql.append(" order by profile.profileName");

		Query query = watchdog.getSessionString().createQuery(sql.toString());

		Page page1 = new Page();
		List<Profile> profileVos = query.setFirstResult(page).setMaxResults(page1.getPerPage()).getResultList();
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObjectList(Profile.formateProfileVos(profileVos));
		return responseVo;
	}

	@Override
	public ResponseVo getProfile(WatchDogVo watchdog, ProfileVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();

		sql.append("from Profile profile where profile.status='Y'");

		if (Objects.nonNull(vo.getProfileId()))
			sql.append("and profile.profileId=:profileId");

		ResponseVo responseVo = new ResponseVo();
		Query query = watchdog.getSessionString().createQuery(sql.toString());
		if (Objects.nonNull(vo.getProfileId()))
			query.setParameter("profileId", vo.getProfileId());

		Profile profile = (Profile) query.getSingleResult();
		responseVo.setObject(Profile.formateProfileVo(profile));
		responseVo.setResponse(true);
		return responseVo;
	}
	 
 

}
