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


import com.dao.LunwenxuantiDao;
import com.entity.LunwenxuantiEntity;
import com.service.LunwenxuantiService;
import com.entity.vo.LunwenxuantiVO;
import com.entity.view.LunwenxuantiView;

@Service("lunwenxuantiService")
public class LunwenxuantiServiceImpl extends ServiceImpl<LunwenxuantiDao, LunwenxuantiEntity> implements LunwenxuantiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<LunwenxuantiEntity> page = this.selectPage(
                new Query<LunwenxuantiEntity>(params).getPage(),
                new EntityWrapper<LunwenxuantiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<LunwenxuantiEntity> wrapper) {
		  Page<LunwenxuantiView> page =new Query<LunwenxuantiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<LunwenxuantiVO> selectListVO(Wrapper<LunwenxuantiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public LunwenxuantiVO selectVO(Wrapper<LunwenxuantiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<LunwenxuantiView> selectListView(Wrapper<LunwenxuantiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public LunwenxuantiView selectView(Wrapper<LunwenxuantiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
