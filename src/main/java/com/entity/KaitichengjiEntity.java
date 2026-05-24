package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 开题成绩
 * 数据库通用操作实体类（普通增删改查）
 */
@TableName("kaitichengji")
public class KaitichengjiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public KaitichengjiEntity() {
		
	}
	
	public KaitichengjiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
    private Long id;
	/**
	 * 报告编号
	 */
					
	private String baogaobianhao;
	
	/**
	 * 课题名称
	 */
					
	private String ketimingcheng;
	
	/**
	 * 课题分类
	 */
					
	private String ketifenlei;
	
	/**
	 * 课题封面
	 */
					
	private String ketifengmian;
	
	/**
	 * 评分
	 */
					
	private Double pingfen;
	
	/**
	 * 意见建议
	 */
					
	private String yijianjianyi;
	
	/**
	 * 具体时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat 		
	private Date jutishijian;
	
	/**
	 * 学生账号
	 */
					
	private String xueshengzhanghao;
	
	/**
	 * 学生姓名
	 */
					
	private String xueshengxingming;
	
	/**
	 * 专业
	 */
					
	private String zhuanye;
	
	/**
	 * 院系
	 */
					
	private String yuanxi;
	
	/**
	 * 班级
	 */
					
	private String banji;
	
	/**
	 * 教师工号
	 */
					
	private String jiaoshigonghao;
	
	/**
	 * 教师姓名
	 */
					
	private String jiaoshixingming;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：报告编号
	 */
	public void setBaogaobianhao(String baogaobianhao) {
		this.baogaobianhao = baogaobianhao;
	}
	/**
	 * 获取：报告编号
	 */
	public String getBaogaobianhao() {
		return baogaobianhao;
	}
	/**
	 * 设置：课题名称
	 */
	public void setKetimingcheng(String ketimingcheng) {
		this.ketimingcheng = ketimingcheng;
	}
	/**
	 * 获取：课题名称
	 */
	public String getKetimingcheng() {
		return ketimingcheng;
	}
	/**
	 * 设置：课题分类
	 */
	public void setKetifenlei(String ketifenlei) {
		this.ketifenlei = ketifenlei;
	}
	/**
	 * 获取：课题分类
	 */
	public String getKetifenlei() {
		return ketifenlei;
	}
	/**
	 * 设置：课题封面
	 */
	public void setKetifengmian(String ketifengmian) {
		this.ketifengmian = ketifengmian;
	}
	/**
	 * 获取：课题封面
	 */
	public String getKetifengmian() {
		return ketifengmian;
	}
	/**
	 * 设置：评分
	 */
	public void setPingfen(Double pingfen) {
		this.pingfen = pingfen;
	}
	/**
	 * 获取：评分
	 */
	public Double getPingfen() {
		return pingfen;
	}
	/**
	 * 设置：意见建议
	 */
	public void setYijianjianyi(String yijianjianyi) {
		this.yijianjianyi = yijianjianyi;
	}
	/**
	 * 获取：意见建议
	 */
	public String getYijianjianyi() {
		return yijianjianyi;
	}
	/**
	 * 设置：具体时间
	 */
	public void setJutishijian(Date jutishijian) {
		this.jutishijian = jutishijian;
	}
	/**
	 * 获取：具体时间
	 */
	public Date getJutishijian() {
		return jutishijian;
	}
	/**
	 * 设置：学生账号
	 */
	public void setXueshengzhanghao(String xueshengzhanghao) {
		this.xueshengzhanghao = xueshengzhanghao;
	}
	/**
	 * 获取：学生账号
	 */
	public String getXueshengzhanghao() {
		return xueshengzhanghao;
	}
	/**
	 * 设置：学生姓名
	 */
	public void setXueshengxingming(String xueshengxingming) {
		this.xueshengxingming = xueshengxingming;
	}
	/**
	 * 获取：学生姓名
	 */
	public String getXueshengxingming() {
		return xueshengxingming;
	}
	/**
	 * 设置：专业
	 */
	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
	/**
	 * 获取：专业
	 */
	public String getZhuanye() {
		return zhuanye;
	}
	/**
	 * 设置：院系
	 */
	public void setYuanxi(String yuanxi) {
		this.yuanxi = yuanxi;
	}
	/**
	 * 获取：院系
	 */
	public String getYuanxi() {
		return yuanxi;
	}
	/**
	 * 设置：班级
	 */
	public void setBanji(String banji) {
		this.banji = banji;
	}
	/**
	 * 获取：班级
	 */
	public String getBanji() {
		return banji;
	}
	/**
	 * 设置：教师工号
	 */
	public void setJiaoshigonghao(String jiaoshigonghao) {
		this.jiaoshigonghao = jiaoshigonghao;
	}
	/**
	 * 获取：教师工号
	 */
	public String getJiaoshigonghao() {
		return jiaoshigonghao;
	}
	/**
	 * 设置：教师姓名
	 */
	public void setJiaoshixingming(String jiaoshixingming) {
		this.jiaoshixingming = jiaoshixingming;
	}
	/**
	 * 获取：教师姓名
	 */
	public String getJiaoshixingming() {
		return jiaoshixingming;
	}

}
