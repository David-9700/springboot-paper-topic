package com.entity.view;

import com.entity.KetifenleiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 课题分类
 * 后端返回视图实体辅助类
 */
@TableName("ketifenlei")
public class KetifenleiView  extends KetifenleiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public KetifenleiView(){
	}
 
 	public KetifenleiView(KetifenleiEntity ketifenleiEntity){
 	try {
			BeanUtils.copyProperties(this, ketifenleiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
