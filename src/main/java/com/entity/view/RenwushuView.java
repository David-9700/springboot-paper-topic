package com.entity.view;

import com.entity.RenwushuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 任务书
 * 后端返回视图实体辅助类
 */
@TableName("renwushu")
public class RenwushuView  extends RenwushuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public RenwushuView(){
	}
 
 	public RenwushuView(RenwushuEntity renwushuEntity){
 	try {
			BeanUtils.copyProperties(this, renwushuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
