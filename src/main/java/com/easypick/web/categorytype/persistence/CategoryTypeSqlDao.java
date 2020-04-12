package com.easypick.web.categorytype.persistence;
 
import java.util.List;
import java.util.Objects;
 
import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.CategoryType; 
import com.easypick.admin.vo.CategoryTypeVo; 
import com.easypick.framework.utility.exception.BussinessException; 
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo; 

@Repository
public class CategoryTypeSqlDao implements CategoryTypeDao {


	@Override
	public ResponseVo saveCategoryType(WatchDogVo watchdog, CategoryTypeVo vo) throws BussinessException {

		CategoryType categoryType = CategoryType.populateCategoryTypeVo(vo);
		watchdog.getSessionString().saveOrUpdate(categoryType);
		vo.setCatTypeId(categoryType.getCatTypeId());
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(vo);
		return responseVo;
	}

	@Override
	public ResponseVo getCategoryTypeList(WatchDogVo watchdog, CategoryTypeVo vo) throws BussinessException {

		StringBuilder sql = new StringBuilder();
		sql.append("from CategoryType type where type.status='Y' ");
		if (Objects.nonNull(vo.getCatTypeCode())) {
			sql.append(" and type.catTypeCode='" + vo.getCatTypeCode() + "'");
		}
		if (Objects.nonNull(vo.getCatTypeName())) {
			sql.append(" and type.catTypeName='" + vo.getCatTypeName() + "'");
		}
		Integer page = 0;
		Integer maxPage = 75;
		if (vo.isPagination()) {
			page = Integer.parseInt(Objects.isNull(vo.getPage()) ? "0" : vo.getPage());
			maxPage = 25;
			if (page > 0) {
				page = page - 1;
				page = page * 25;

			}
		}

		sql.append(" order by type.catTypeName ");
		List<CategoryType> categoryTypeVos = watchdog.getSessionString().createQuery(sql.toString())
				.setFirstResult(page).setMaxResults(maxPage).getResultList();

		ResponseVo responseVo = new ResponseVo();

		responseVo.setObjectList(CategoryType.formateCategoryTypeVos(categoryTypeVos));

		return responseVo;
	}

	@Override
	public ResponseVo getCategoryType(WatchDogVo watchdog, CategoryTypeVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();
		sql.append("from CategoryType type where type.status='Y' and type.catTypeId=:id ");
		ResponseVo responseVo = new ResponseVo();
		CategoryType categoryType = (CategoryType) watchdog.getSessionString().createQuery(sql.toString())
				.setParameter("id", vo.getCatTypeId()).getSingleResult();
		responseVo.setObject(CategoryType.formateCategoryTypeVo(categoryType));
		responseVo.setResponse(true);
		return responseVo;
	}

	    

}
