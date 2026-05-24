package com.entity.view;

import com.entity.YuanxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 院系
 * 后端返回视图实体辅助类
 */
@TableName("yuanxi")
public class YuanxiView  extends YuanxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public YuanxiView(){
	}
 
 	public YuanxiView(YuanxiEntity yuanxiEntity){
 	try {
			BeanUtils.copyProperties(this, yuanxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
