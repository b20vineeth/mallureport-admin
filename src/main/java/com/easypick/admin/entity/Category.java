package com.easypick.admin.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.easypick.admin.vo.CategoryVo;
import com.easypick.framework.utility.vo.AbstractVo;

@Entity
@Table(name = "category", uniqueConstraints = { @UniqueConstraint(columnNames = { "catcod" }) })
public class Category {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer catId;

	@Column(name = "catnam")
	private String categoryName;

	@Column(name = "catcod")
	private String categoryCode;

	@ManyToOne(fetch = FetchType.LAZY)
	private CategoryType categoryType;

	@Column(name = "status")
	private String status;
	@Column(name = "gender")
	private String gender;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Language language;
	
	 
	@Column(name = "thumbnail")
	private String thumbnail;
	
	
	

 

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public CategoryType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static Category populateCategoryVo(CategoryVo vo) {
		Category category = new Category();
		category.setCategoryCode(vo.getCatCode());
		category.setCategoryName(vo.getCatName());
		category.setStatus("Y");
		if (vo.getLang() > 0) {
			Language lan = new Language();
			lan.setId(vo.getLang());
			category.setLanguage(lan);
		}
		 

		CategoryType type = new CategoryType();
		type.setCatTypeId(Integer.parseInt(vo.getCatType()));

		category.setCategoryType(type);
		category.setGender(vo.getGender());

		if (vo.getCatId() != 0)
			category.setCatId(vo.getCatId());
		return category;
	}

	public static List<? extends AbstractVo> formateCategoryVos(List<Category> categoryVos) {
		List<CategoryVo> vos = new ArrayList<>();
		CategoryVo categoryVo = null;
		for (Category category : categoryVos) {
			categoryVo = new CategoryVo();
			categoryVo.setCatId(category.getCatId());
			categoryVo.setCatCode(category.getCategoryCode());
			categoryVo.setCatName(category.getCategoryName());
			CategoryType categoryType = category.getCategoryType();
			categoryVo.setCatType(categoryType.getCatTypeName());
			categoryVo.setGender(category.getGender());
			if (Objects.nonNull(category.getLanguage())) {
				categoryVo.setLang(category.getLanguage().getId());
				categoryVo.setLangName(category.getLanguage().getLangName());
			}

			vos.add(categoryVo);
		}

		return vos;
	}

	public static Object formateCategoryVo(Category category) {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setCatId(category.getCatId());
		categoryVo.setCatCode(category.getCategoryCode());
		categoryVo.setCatName(category.getCategoryName());
		categoryVo.setCatType(category.getCategoryType().getCatTypeName());
		categoryVo.setCatTypeId(category.getCategoryType().getCatTypeId());
		categoryVo.setGender(category.getGender());
		return categoryVo;
	}

}
