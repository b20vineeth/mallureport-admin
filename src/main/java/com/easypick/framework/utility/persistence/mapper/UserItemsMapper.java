package com.easypick.framework.utility.persistence.mapper;
 
import java.text.ParseException;
import java.util.Objects;
 
import com.easypick.admin.vo.UserSetupVo;
import com.easypick.framework.utility.commonUtility.DateOperation; 

public class UserItemsMapper {

	public   UserSetupVo Mapper(Object[] items) throws ParseException {
		UserSetupVo vo=new UserSetupVo();
		//vo.setUserId(Objects.nonNull(items[0])?items[0].toString():"");
		vo.setEmail(Objects.nonNull(items[1])?items[1].toString():"");
		vo.setUsername(Objects.nonNull(items[2])?items[2].toString():"");
		vo.setCompanyCode(Objects.nonNull(items[3])?items[3].toString():"");
		 
			if(Objects.nonNull(items[4]))
					vo.setCreateDateFromat(new DateOperation().formateDate(items[4]));
			if(Objects.nonNull(items[5]))
				vo.setValiditytoFromat(new DateOperation().formateDate(items[5]));
			if(Objects.nonNull(items[6]))
				vo.setValidityFromFromat(new DateOperation().formateDate(items[6]));
		
		vo.setFirstName(Objects.nonNull(items[7])?items[7].toString():"");
		vo.setLastName(Objects.nonNull(items[8])?items[8].toString():"");
		vo.setStatus(Objects.nonNull(items[9])?items[9].toString():"");
		return vo;
	}

}
