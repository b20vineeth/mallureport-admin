package com.easypick.admin.vo;

import java.util.List;
import java.util.Objects;

import com.easypick.framework.utility.vo.AbstractVo;

public class DataVo implements AbstractVo {
	
	private String tag;
	private Integer value;
	private List<? extends AbstractVo> suggestions;
	
	
	
	
	
	public List<? extends AbstractVo> getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(List<? extends AbstractVo> suggestions) {
		this.suggestions = suggestions;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	 
	
	public static void populateDataVo(List<Object[]> object, List<DataVo> vos) {
		DataVo dataVo;
		for (Object[] items : object) {
			dataVo = new DataVo();
			try {
				if (Objects.nonNull(items[0]))
					dataVo.setValue(Integer.parseInt(items[0].toString()));
				if (Objects.nonNull(items[1]))
					dataVo.setTag(items[1].toString());
				vos.add(dataVo);
			} catch (Exception e) {

			}
		}
	}

}
