package com.entity.view;

import com.entity.LunwenxuantiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 论文选题
 * 后端返回视图实体辅助类
 */
@TableName("lunwenxuanti")
public class LunwenxuantiView  extends LunwenxuantiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public LunwenxuantiView(){
	}
 
 	public LunwenxuantiView(LunwenxuantiEntity lunwenxuantiEntity){
 	try {
			BeanUtils.copyProperties(this, lunwenxuantiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
