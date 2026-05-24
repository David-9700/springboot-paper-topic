package com.dao;

import com.entity.KaitichengjiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.KaitichengjiVO;
import com.entity.view.KaitichengjiView;


/**
 * 开题成绩
 */
public interface KaitichengjiDao extends BaseMapper<KaitichengjiEntity> {
	
	List<KaitichengjiVO> selectListVO(@Param("ew") Wrapper<KaitichengjiEntity> wrapper);
	
	KaitichengjiVO selectVO(@Param("ew") Wrapper<KaitichengjiEntity> wrapper);
	
	List<KaitichengjiView> selectListView(@Param("ew") Wrapper<KaitichengjiEntity> wrapper);

	List<KaitichengjiView> selectListView(Pagination page,@Param("ew") Wrapper<KaitichengjiEntity> wrapper);

	
	KaitichengjiView selectView(@Param("ew") Wrapper<KaitichengjiEntity> wrapper);
	

}
