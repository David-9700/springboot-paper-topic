package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.RenwushuDao;
import com.entity.RenwushuEntity;
import com.service.RenwushuService;
import com.entity.vo.RenwushuVO;
import com.entity.view.RenwushuView;

@Service("renwushuService")
public class RenwushuServiceImpl extends ServiceImpl<RenwushuDao, RenwushuEntity> implements RenwushuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RenwushuEntity> page = this.selectPage(
                new Query<RenwushuEntity>(params).getPage(),
                new EntityWrapper<RenwushuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<RenwushuEntity> wrapper) {
		  Page<RenwushuView> page =new Query<RenwushuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<RenwushuVO> selectListVO(Wrapper<RenwushuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public RenwushuVO selectVO(Wrapper<RenwushuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<RenwushuView> selectListView(Wrapper<RenwushuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public RenwushuView selectView(Wrapper<RenwushuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
