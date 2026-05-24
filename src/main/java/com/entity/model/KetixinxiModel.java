package com.entity.model;

import com.entity.KetixinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 课题信息
 * 接收传参的实体类
 * 取自ModelAndView 的model名称
 */
public class KetixinxiModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 课题分类
	 */
	
	private String ketifenlei;
		
	/**
	 * 课题封面
	 */
	
	private String ketifengmian;
		
	/**
	 * 院系
	 */
	
	private String yuanxi;
		
	/**
	 * 专业
	 */
	
	private String zhuanye;
		
	/**
	 * 课题状态
	 */
	
	private String ketizhuangtai;
		
	/**
	 * 可选人数
	 */
	
	private Integer renshu;
		
	/**
	 * 课题要求
	 */
	
	private String ketiyaoqiu;
		
	/**
	 * 课题内容
	 */
	
	private String ketineirong;
		
	/**
	 * 开题日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date kaitiriqi;
		
	/**
	 * 发布时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date fabushijian;
		
	/**
	 * 教师工号
	 */
	
	private String jiaoshigonghao;
		
	/**
	 * 教师姓名
	 */
	
	private String jiaoshixingming;
		
	/**
	 * 电话号码
	 */
	
	private String dianhuahaoma;
		
	/**
	 * 是否审核
	 */
	
	private String sfsh;
		
	/**
	 * 审核回复
	 */
	
	private String shhf;
				
	
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
	 * 设置：课题状态
	 */
	 
	public void setKetizhuangtai(String ketizhuangtai) {
		this.ketizhuangtai = ketizhuangtai;
	}
	
	/**
	 * 获取：课题状态
	 */
	public String getKetizhuangtai() {
		return ketizhuangtai;
	}
				
	
	/**
	 * 设置：可选人数
	 */
	 
	public void setRenshu(Integer renshu) {
		this.renshu = renshu;
	}
	
	/**
	 * 获取：可选人数
	 */
	public Integer getRenshu() {
		return renshu;
	}
				
	
	/**
	 * 设置：课题要求
	 */
	 
	public void setKetiyaoqiu(String ketiyaoqiu) {
		this.ketiyaoqiu = ketiyaoqiu;
	}
	
	/**
	 * 获取：课题要求
	 */
	public String getKetiyaoqiu() {
		return ketiyaoqiu;
	}
				
	
	/**
	 * 设置：课题内容
	 */
	 
	public void setKetineirong(String ketineirong) {
		this.ketineirong = ketineirong;
	}
	
	/**
	 * 获取：课题内容
	 */
	public String getKetineirong() {
		return ketineirong;
	}
				
	
	/**
	 * 设置：开题日期
	 */
	 
	public void setKaitiriqi(Date kaitiriqi) {
		this.kaitiriqi = kaitiriqi;
	}
	
	/**
	 * 获取：开题日期
	 */
	public Date getKaitiriqi() {
		return kaitiriqi;
	}
				
	
	/**
	 * 设置：发布时间
	 */
	 
	public void setFabushijian(Date fabushijian) {
		this.fabushijian = fabushijian;
	}
	
	/**
	 * 获取：发布时间
	 */
	public Date getFabushijian() {
		return fabushijian;
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
	 * 设置：电话号码
	 */
	 
	public void setDianhuahaoma(String dianhuahaoma) {
		this.dianhuahaoma = dianhuahaoma;
	}
	
	/**
	 * 获取：电话号码
	 */
	public String getDianhuahaoma() {
		return dianhuahaoma;
	}
				
	
	/**
	 * 设置：是否审核
	 */
	 
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
				
	
	/**
	 * 设置：审核回复
	 */
	 
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}
			
}
