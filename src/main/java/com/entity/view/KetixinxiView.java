package com.entity.view;

import com.entity.KetixinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 课题信息
 * 后端返回视图实体辅助类
 */
@TableName("ketixinxi")
public class KetixinxiView  extends KetixinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public KetixinxiView(){
	}
 
 	public KetixinxiView(KetixinxiEntity ketixinxiEntity){
 	try {
			BeanUtils.copyProperties(this, ketixinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
