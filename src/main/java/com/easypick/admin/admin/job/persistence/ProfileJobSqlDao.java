package com.easypick.admin.admin.job.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.easypick.admin.vo.DataVo;
import com.easypick.admin.vo.ProfileVo;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.vo.AbstractVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Component
public class ProfileJobSqlDao implements ProfileDao {

	String sql = null;
	private String filterType = null;
	int resultSize = 6;
	WatchDogVo watchdog;

	@Override
	public Map<String, List<? extends AbstractVo>> getActress(WatchDogVo watchdog) {
		this.watchdog = watchdog;
		Map<String, List<? extends AbstractVo>> map = new HashMap<>();
		this.filterType = "A";
		List<DataVo> langVos = getLanguageVos(StringUitity.getLanguage());
		for (DataVo lang : langVos) {
			StringBuilder sql = baseQuery();
			findProfileDetails(map, lang, sql.toString());
		}
		return map;
	}

	@Override
	public Map<String, List<? extends AbstractVo>> getPopularProfile(WatchDogVo watchdog) {
		this.watchdog = watchdog;
		Map<String, List<? extends AbstractVo>> map = new HashMap<>();
		StringBuilder sql = baseQuery();
		findPopularProfileDetails(map, sql.toString());

		return map;
	}

	private void findPopularProfileDetails(Map<String, List<? extends AbstractVo>> map, String string) {
		sql += " and tag like '%popular%' ";
		sql += " order by profileid desc  ";
		Query query = this.watchdog.getSessionString().createSQLQuery(sql);
		List<ProfileVo> vos = excuteQuery(query);
		map.put("popular", vos);

	}

	@Override
	public Map<String, List<? extends AbstractVo>> getActor(WatchDogVo watchdog) {
		this.watchdog = watchdog;
		Map<String, List<? extends AbstractVo>> map = new HashMap<>();
		this.filterType = "F";
		List<DataVo> langVos = getLanguageVos(StringUitity.getLanguage());
		for (DataVo lang : langVos) {
			StringBuilder sql = baseQuery();
			findProfileDetails(map, lang, sql.toString());
		}
		return map;
	}

	private void findProfileDetails(Map<String, List<? extends AbstractVo>> map, DataVo lang, String sql) {

		if ("M".equals(this.filterType) || "F".equals(this.filterType) || "O".equals(this.filterType)) {
			sql += " and gender='" + this.filterType + "'";
		}
		sql+=" order by profileid desc";
		Query query = this.watchdog.getSessionString().createSQLQuery(sql);
		List<ProfileVo> vos = excuteQuery(query);
		map.put(lang.getLangCode(), vos);

	}

	private List<DataVo> getLanguageVos(List<String> languages) {
		String lang = "";
		for (String language : languages) {
			lang += "'" + language + "',";
		}
		String sql = "SELECT id,langcod  FROM  langsetup   where status ='Y' and  langcod in ("
				+ lang.replaceAll(",$", "") + ")";
		Query query = watchdog.getSessionString().createQuery(sql);
		List<Object[]> object = query.setFirstResult(0).setMaxResults(this.resultSize).getResultList();
		List<DataVo> vos = new ArrayList<>();
		DataVo vo = new DataVo();
		if (object.size() > 0) {
			for (Object[] items : object) {
				vo = new DataVo();
				vo.setLangId(items[0].toString());
				vo.setLangCode(items[1].toString());
				vos.add(vo);
			}
		}
		return vos;
	}

	private StringBuilder baseQuery() {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT profile_name,profile_code,thumbnail,case when profile.language IS NULL or profile.language = ''  then ' ' else  (SELECT GROUP_CONCAT( concat(langcod, '#', langnam)) FROM langsetup  where  id in (REPLACE(profile.language,'#','')))  end  language "
						+ " FROM profile  WHERE status='Y' and  thumbnail is not NULL");
		return sql;
	}

	private List<ProfileVo> excuteQuery(Query query) {

		List<Object[]> object = query.setFirstResult(0).setMaxResults(this.resultSize).getResultList();

		List<ProfileVo> vos = new ArrayList<>();

		if (object.size() > 0) {
			for (Object[] items : object) {
				ProfileVo.populate(object, vos);
			}

		}
		return vos;
	}

}
