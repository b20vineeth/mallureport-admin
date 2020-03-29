package com.easypick.admin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.easypick.admin.vo.CategoryTypeVo;
import com.easypick.framework.utility.vo.AbstractVo;  

@Entity
@Table(name = "category_type", uniqueConstraints = { @UniqueConstraint(columnNames = { "cattypecod"}) } )
public class CategoryType   {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer catTypeId;
	 
	@Column(name = "cattypenam")
	private String catTypeName;
	
	@Column(name = "cattypecod")
	private String catTypeCode;
	 
	@Column(name = "status")
	private String status="Y";

	public Integer getCatTypeId() {
		return catTypeId;
	}

	public void setCatTypeId(Integer catTypeId) {
		this.catTypeId = catTypeId;
	}

	public String getCatTypeName() {
		return catTypeName;
	}

	public void setCatTypeName(String catTypeName) {
		this.catTypeName = catTypeName;
	}

	public String getCatTypeCode() {
		return catTypeCode;
	}

	public void setCatTypeCode(String catTypeCode) {
		this.catTypeCode = catTypeCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static Object formateCategoryTypeVo(CategoryType categoryType) {
		CategoryTypeVo categoryTypeVo = new CategoryTypeVo();
		categoryTypeVo.setCatTypeId(categoryType.getCatTypeId());
		categoryTypeVo.setCatTypeCode(categoryType.getCatTypeCode());
		categoryTypeVo.setCatTypeName(categoryType.getCatTypeName());
		return categoryTypeVo;
	}

	public static List<? extends AbstractVo> formateCategoryTypeVos(List<CategoryType> categoryTypeVos) {
		List<CategoryTypeVo> vos = new ArrayList<>();
		CategoryTypeVo vo = null;
		for (CategoryType categoryType : categoryTypeVos) {
			vo = new CategoryTypeVo();
			vo.setCatTypeCode(categoryType.getCatTypeCode());
			vo.setCatTypeName(categoryType.getCatTypeName());
			vo.setCatTypeId(categoryType.getCatTypeId());
			vos.add(vo);
		}

		return vos;
	}

	public static CategoryType populateCategoryTypeVo(CategoryTypeVo vo) {
		CategoryType categoryType = new CategoryType();
		categoryType.setCatTypeCode(vo.getCatTypeCode());
		categoryType.setCatTypeName(vo.getCatTypeName());
		categoryType.setStatus("Y");

		if (vo.getCatTypeId() != 0)
			categoryType.setCatTypeId(vo.getCatTypeId());
		return categoryType;
	}


 
	 
	 
  
}
