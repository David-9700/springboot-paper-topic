package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.XuantixinxiEntity;
import com.entity.view.XuantixinxiView;

import com.service.XuantixinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 选题信息
 * 后端接口
 */
@RestController
@RequestMapping("/xuantixinxi")
public class XuantixinxiController {
    @Autowired
    private XuantixinxiService xuantixinxiService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XuantixinxiEntity xuantixinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			xuantixinxi.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiaoshi")) {
			xuantixinxi.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<XuantixinxiEntity> ew = new EntityWrapper<XuantixinxiEntity>();

		PageUtils page = xuantixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuantixinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XuantixinxiEntity xuantixinxi, 
		HttpServletRequest request){
        EntityWrapper<XuantixinxiEntity> ew = new EntityWrapper<XuantixinxiEntity>();

		PageUtils page = xuantixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuantixinxi), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XuantixinxiEntity xuantixinxi){
       	EntityWrapper<XuantixinxiEntity> ew = new EntityWrapper<XuantixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xuantixinxi, "xuantixinxi")); 
        return R.ok().put("data", xuantixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XuantixinxiEntity xuantixinxi){
        EntityWrapper< XuantixinxiEntity> ew = new EntityWrapper< XuantixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xuantixinxi, "xuantixinxi")); 
		XuantixinxiView xuantixinxiView =  xuantixinxiService.selectView(ew);
		return R.ok("查询选题信息成功").put("data", xuantixinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XuantixinxiEntity xuantixinxi = xuantixinxiService.selectById(id);
        return R.ok().put("data", xuantixinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XuantixinxiEntity xuantixinxi = xuantixinxiService.selectById(id);
        return R.ok().put("data", xuantixinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XuantixinxiEntity xuantixinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xuantixinxi);
        xuantixinxiService.insert(xuantixinxi);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XuantixinxiEntity xuantixinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xuantixinxi);
        xuantixinxiService.insert(xuantixinxi);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XuantixinxiEntity xuantixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuantixinxi);
        xuantixinxiService.updateById(xuantixinxi);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<XuantixinxiEntity> list = new ArrayList<XuantixinxiEntity>();
        for(Long id : ids) {
            XuantixinxiEntity xuantixinxi = xuantixinxiService.selectById(id);
            xuantixinxi.setSfsh(sfsh);
            xuantixinxi.setShhf(shhf);
            list.add(xuantixinxi);
        }
        xuantixinxiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xuantixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	





    @RequestMapping("/importExcel")
    public R importExcel(@RequestParam("file") MultipartFile file){
        try {
            //获取输入流
            InputStream inputStream = file.getInputStream();
            //创建读取工作簿
            Workbook workbook = WorkbookFactory.create(inputStream);
            //获取工作表
            Sheet sheet = workbook.getSheetAt(0);
            //获取总行
            int rows=sheet.getPhysicalNumberOfRows();
            if(rows>1){
                //获取单元格
                for (int i = 1; i < rows; i++) {
                    Row row = sheet.getRow(i);
                    XuantixinxiEntity xuantixinxiEntity =new XuantixinxiEntity();
                    xuantixinxiEntity.setId(new Date().getTime());
                     
                    //想数据库中添加新对象
                    xuantixinxiService.insert(xuantixinxiEntity);//方法
                }
            }
            inputStream.close();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok("导入成功");
    }





}
