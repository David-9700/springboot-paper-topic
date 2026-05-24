package com.dao;

import com.entity.RenwushuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.RenwushuVO;
import com.entity.view.RenwushuView;


/**
 * 任务书
 */
public interface RenwushuDao extends BaseMapper<RenwushuEntity> {
	
	List<RenwushuVO> selectListVO(@Param("ew") Wrapper<RenwushuEntity> wrapper);
	
	RenwushuVO selectVO(@Param("ew") Wrapper<RenwushuEntity> wrapper);
	
	List<RenwushuView> selectListView(@Param("ew") Wrapper<RenwushuEntity> wrapper);

	List<RenwushuView> selectListView(Pagination page,@Param("ew") Wrapper<RenwushuEntity> wrapper);

	
	RenwushuView selectView(@Param("ew") Wrapper<RenwushuEntity> wrapper);
	

}
