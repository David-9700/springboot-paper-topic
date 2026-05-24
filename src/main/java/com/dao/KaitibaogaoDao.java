package com.dao;

import com.entity.KaitibaogaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.KaitibaogaoVO;
import com.entity.view.KaitibaogaoView;


/**
 * 开题报告
 */
public interface KaitibaogaoDao extends BaseMapper<KaitibaogaoEntity> {
	
	List<KaitibaogaoVO> selectListVO(@Param("ew") Wrapper<KaitibaogaoEntity> wrapper);
	
	KaitibaogaoVO selectVO(@Param("ew") Wrapper<KaitibaogaoEntity> wrapper);
	
	List<KaitibaogaoView> selectListView(@Param("ew") Wrapper<KaitibaogaoEntity> wrapper);

	List<KaitibaogaoView> selectListView(Pagination page,@Param("ew") Wrapper<KaitibaogaoEntity> wrapper);

	
	KaitibaogaoView selectView(@Param("ew") Wrapper<KaitibaogaoEntity> wrapper);
	

}
