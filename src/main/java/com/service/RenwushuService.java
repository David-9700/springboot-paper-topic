package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.RenwushuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.RenwushuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.RenwushuView;


/**
 * 任务书
 */
public interface RenwushuService extends IService<RenwushuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<RenwushuVO> selectListVO(Wrapper<RenwushuEntity> wrapper);
   	
   	RenwushuVO selectVO(@Param("ew") Wrapper<RenwushuEntity> wrapper);
   	
   	List<RenwushuView> selectListView(Wrapper<RenwushuEntity> wrapper);
   	
   	RenwushuView selectView(@Param("ew") Wrapper<RenwushuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<RenwushuEntity> wrapper);

   	

}

