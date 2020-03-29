package com.easypick.admin.gallery.persistence; 
import org.springframework.stereotype.Repository;
 
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.persistence.Dao; 
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.framework.utility.vo.WatchDogVo;

@Repository
public class CollectGalleryDataByFourParameter implements Dao {

	@Override
	public ResponseVo execute(WatchDogVo watchDogVo, ResponseVo vo) throws BussinessException {
		return null;
		
		 
	}

}
