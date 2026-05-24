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


import com.dao.KaitichengjiDao;
import com.entity.KaitichengjiEntity;
import com.service.KaitichengjiService;
import com.entity.vo.KaitichengjiVO;
import com.entity.view.KaitichengjiView;

@Service("kaitichengjiService")
public class KaitichengjiServiceImpl extends ServiceImpl<KaitichengjiDao, KaitichengjiEntity> implements KaitichengjiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KaitichengjiEntity> page = this.selectPage(
                new Query<KaitichengjiEntity>(params).getPage(),
                new EntityWrapper<KaitichengjiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<KaitichengjiEntity> wrapper) {
		  Page<KaitichengjiView> page =new Query<KaitichengjiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<KaitichengjiVO> selectListVO(Wrapper<KaitichengjiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KaitichengjiVO selectVO(Wrapper<KaitichengjiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KaitichengjiView> selectListView(Wrapper<KaitichengjiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KaitichengjiView selectView(Wrapper<KaitichengjiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
