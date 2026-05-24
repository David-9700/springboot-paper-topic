package com.dao;

import com.entity.LunwenxuantiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.LunwenxuantiVO;
import com.entity.view.LunwenxuantiView;


/**
 * 论文选题
 */
public interface LunwenxuantiDao extends BaseMapper<LunwenxuantiEntity> {
	
	List<LunwenxuantiVO> selectListVO(@Param("ew") Wrapper<LunwenxuantiEntity> wrapper);
	
	LunwenxuantiVO selectVO(@Param("ew") Wrapper<LunwenxuantiEntity> wrapper);
	
	List<LunwenxuantiView> selectListView(@Param("ew") Wrapper<LunwenxuantiEntity> wrapper);

	List<LunwenxuantiView> selectListView(Pagination page,@Param("ew") Wrapper<LunwenxuantiEntity> wrapper);

	
	LunwenxuantiView selectView(@Param("ew") Wrapper<LunwenxuantiEntity> wrapper);
	

}
