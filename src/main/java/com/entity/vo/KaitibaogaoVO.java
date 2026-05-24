package com.entity.vo;

import com.entity.KaitibaogaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 开题报告
 */
public class KaitibaogaoVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 课题名称
	 */
	
	private String ketimingcheng;
		
	/**
	 * 课题分类
	 */
	
	private String ketifenlei;
		
	/**
	 * 报告内容
	 */
	
	private String baogaoneirong;
		
	/**
	 * 课题封面
	 */
	
	private String ketifengmian;
		
	/**
	 * 文献综述
	 */
	
	private String wenxianzongshu;
		
	/**
	 * 资料文件
	 */
	
	private String ziliaowenjian;
		
	/**
	 * 上传时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date shangchuanshijian;
		
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
		
	/**
	 * 回复内容
	 */
	
	private String shhf;
				
	
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
	 * 设置：报告内容
	 */
	 
	public void setBaogaoneirong(String baogaoneirong) {
		this.baogaoneirong = baogaoneirong;
	}
	
	/**
	 * 获取：报告内容
	 */
	public String getBaogaoneirong() {
		return baogaoneirong;
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
	 * 设置：文献综述
	 */
	 
	public void setWenxianzongshu(String wenxianzongshu) {
		this.wenxianzongshu = wenxianzongshu;
	}
	
	/**
	 * 获取：文献综述
	 */
	public String getWenxianzongshu() {
		return wenxianzongshu;
	}
				
	
	/**
	 * 设置：资料文件
	 */
	 
	public void setZiliaowenjian(String ziliaowenjian) {
		this.ziliaowenjian = ziliaowenjian;
	}
	
	/**
	 * 获取：资料文件
	 */
	public String getZiliaowenjian() {
		return ziliaowenjian;
	}
				
	
	/**
	 * 设置：上传时间
	 */
	 
	public void setShangchuanshijian(Date shangchuanshijian) {
		this.shangchuanshijian = shangchuanshijian;
	}
	
	/**
	 * 获取：上传时间
	 */
	public Date getShangchuanshijian() {
		return shangchuanshijian;
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
				
	
	/**
	 * 设置：回复内容
	 */
	 
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	
	/**
	 * 获取：回复内容
	 */
	public String getShhf() {
		return shhf;
	}
			
}
