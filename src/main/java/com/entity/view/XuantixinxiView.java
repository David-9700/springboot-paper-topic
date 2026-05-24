package com.entity.view;

import com.entity.XuantixinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 选题信息
 * 后端返回视图实体辅助类
 */
@TableName("xuantixinxi")
public class XuantixinxiView  extends XuantixinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public XuantixinxiView(){
	}
 
 	public XuantixinxiView(XuantixinxiEntity xuantixinxiEntity){
 	try {
			BeanUtils.copyProperties(this, xuantixinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
