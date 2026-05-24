package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.LunwenxuantiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.LunwenxuantiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.LunwenxuantiView;


/**
 * 论文选题
 */
public interface LunwenxuantiService extends IService<LunwenxuantiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<LunwenxuantiVO> selectListVO(Wrapper<LunwenxuantiEntity> wrapper);
   	
   	LunwenxuantiVO selectVO(@Param("ew") Wrapper<LunwenxuantiEntity> wrapper);
   	
   	List<LunwenxuantiView> selectListView(Wrapper<LunwenxuantiEntity> wrapper);
   	
   	LunwenxuantiView selectView(@Param("ew") Wrapper<LunwenxuantiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<LunwenxuantiEntity> wrapper);

   	

}

