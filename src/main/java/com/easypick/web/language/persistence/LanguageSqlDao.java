package com.easypick.web.language.persistence;
 
import java.util.List;
import java.util.Objects;

import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.Language; 
import com.easypick.admin.vo.LanguageVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo; 

@Repository
public class LanguageSqlDao implements LanguageDao {

	private WatchDogVo watchdog;
	
	@Override
	public ResponseVo saveLanguage(WatchDogVo watchdog, LanguageVo vo) throws BussinessException {
		
		this.watchdog=watchdog;
		removechildData(vo);
		Language language = Language.populateLangauageVo(vo);
		watchdog.getSessionString().saveOrUpdate(language);
		vo.setLangId(language.getId());
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(vo);
		return responseVo;
	}

	private void removechildData(LanguageVo vo) {

		if (vo.getLangId() != 0 ) {
			SQLQuery q = this.watchdog.getSessionString().createSQLQuery("delete from testtable where lang_id = "+vo.getLangId());
			q.executeUpdate();
		}
		
	}

	@Override
	public ResponseVo getLanguageList(WatchDogVo watchdog, LanguageVo vo) throws BussinessException {

		StringBuilder sql = new StringBuilder();
		sql.append("from Language lang where lang.status='Y' ");
		if (Objects.nonNull(vo.getLangCode())) {
			sql.append(" and lang.langCode='" + vo.getLangCode() + "'");
		}
		if (Objects.nonNull(vo.getLangName())) {
			sql.append(" and lang.langName='" + vo.getLangName() + "'");
		}
		Integer page = Integer.parseInt(Objects.isNull(vo.getPage()) ? "0" : vo.getPage());
		Page page1 = new Page();
		page1.setCurrentPage(page);
		if (page > 0)
			page = page - 1;
		page = page *  page1.getPerPage();
		sql.append(" order by lang.langName ");
		List<Language> langVos = watchdog.getSessionString().createQuery(sql.toString()).setFirstResult(page)
				.setMaxResults(page1.getPerPage()).getResultList();
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObjectList(Language.formateVos(langVos));
		
		Query query = watchdog.getSessionString().createQuery("Select 1 " + sql.toString());
		List<Language> movieCount = query.getResultList();
		page1.setTotalResult(movieCount.size());
		page1.updateTotalPage();
		responseVo.setPage(page1);
		return responseVo;
	}

	@Override
	public ResponseVo getLanguage(WatchDogVo watchdog, LanguageVo vo) throws BussinessException {

		StringBuilder sql = new StringBuilder();
		sql.append("from Language lang where lang.status='Y' and lang.id=:id ");
		ResponseVo responseVo = new ResponseVo();
		Language lang = (Language) watchdog.getSessionString().createQuery(sql.toString())
				.setParameter("id", vo.getLangId()).getSingleResult();
		responseVo.setObject(Language.formateVo(lang));
		responseVo.setResponse(true);
		return responseVo;
	}

	    

}
