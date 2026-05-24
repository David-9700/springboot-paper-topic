package com.entity.view;

import com.entity.KaitichengjiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 开题成绩
 * 后端返回视图实体辅助类
 */
@TableName("kaitichengji")
public class KaitichengjiView  extends KaitichengjiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public KaitichengjiView(){
	}
 
 	public KaitichengjiView(KaitichengjiEntity kaitichengjiEntity){
 	try {
			BeanUtils.copyProperties(this, kaitichengjiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
