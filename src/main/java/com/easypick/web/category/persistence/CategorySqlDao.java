package com.easypick.web.category.persistence;
 
import java.util.List;
import java.util.Objects;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.easypick.admin.entity.Category;
import com.easypick.admin.vo.CategoryVo;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.Page;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo; 

@Repository
public class CategorySqlDao implements CategoryDao {

	 
	@Override
	public ResponseVo getCategory(WatchDogVo watchdog, CategoryVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();
		sql.append("from Category cat where cat.status='Y' and cat.catId=:id ");
		ResponseVo responseVo = new ResponseVo();
		Category category = (Category) watchdog.getSessionString().createQuery(sql.toString())
				.setParameter("id", vo.getCatId()).getSingleResult();
		responseVo.setObject(Category.formateCategoryVo(category));
		responseVo.setResponse(true);
		return responseVo;
	}

	@Override
	public ResponseVo getCategoryList(WatchDogVo watchdog, CategoryVo vo) throws BussinessException {
		StringBuilder sql = new StringBuilder();
		Page page1 = new Page();
		sql.append("from Category cat where cat.status='Y' ");
		if (Objects.nonNull(vo.getCatCode()) && vo.getCatCode().trim().length() > 0) {
			sql.append(" and cat.categoryCode like '%" + vo.getCatCode() + "%'");
		}
		if (Objects.nonNull(vo.getCatName()) && vo.getCatName().trim().length() > 0) {
			sql.append(" and cat.categoryName like '%" + vo.getCatName() + "%'");
		}
		if (Objects.nonNull(vo.getCatType()) && vo.getCatType().trim().length() > 0) {
			sql.append(" and cat.categoryType.catTypeId='" + vo.getCatType() + "'");
		}

		if (Objects.nonNull(vo.getLang()) && vo.getLang() > 0) {
			sql.append(" and cat.language.id='" + vo.getLang() + "'");
		}
		if (Objects.nonNull(vo.getGender()) && vo.getGender().trim().length() > 0 && !"0".equals(vo.getGender())) {
			sql.append(" and cat.gender='" + vo.getGender() + "'");
		}

		Integer page = Integer.parseInt(Objects.isNull(vo.getPage()) ? "0" : vo.getPage());
		if (page > 0)
			page = page - 1;
		page = page * page1.getPerPage();
		sql.append(" order by cat.categoryName ");
		if (vo.getPerPage() != 0) {
			page1.setPerPage(vo.getPerPage());
		}

		List<Category> categoryVos = watchdog.getSessionString().createQuery(sql.toString()).setFirstResult(page)
				.setMaxResults(page1.getPerPage()).getResultList();
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObjectList(Category.formateCategoryVos(categoryVos));

		Query query = watchdog.getSessionString().createQuery("select count(*) " + sql);
		Long count = (Long) query.uniqueResult();

		page1.setTotalResult(Integer.parseInt(count.toString()));
		page1.setCurrentPage(page);
		page1.updateTotalPage();
		responseVo.setPage(page1);

		return responseVo;
	}

	@Override
	public ResponseVo saveCategory(WatchDogVo watchdog, CategoryVo vo) throws BussinessException {
		Category category = Category.populateCategoryVo(vo);
		watchdog.getSessionString().saveOrUpdate(category);
		vo.setCatId(category.getCatId());
		ResponseVo responseVo = new ResponseVo();
		responseVo.setObject(vo);
		return responseVo;
	}

	      

}
