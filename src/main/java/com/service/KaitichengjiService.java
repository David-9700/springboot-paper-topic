package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.KaitichengjiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.KaitichengjiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.KaitichengjiView;


/**
 * 开题成绩
 */
public interface KaitichengjiService extends IService<KaitichengjiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KaitichengjiVO> selectListVO(Wrapper<KaitichengjiEntity> wrapper);
   	
   	KaitichengjiVO selectVO(@Param("ew") Wrapper<KaitichengjiEntity> wrapper);
   	
   	List<KaitichengjiView> selectListView(Wrapper<KaitichengjiEntity> wrapper);
   	
   	KaitichengjiView selectView(@Param("ew") Wrapper<KaitichengjiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KaitichengjiEntity> wrapper);

   	

}

