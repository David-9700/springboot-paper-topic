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

import com.entity.KaitibaogaoEntity;
import com.entity.view.KaitibaogaoView;

import com.service.KaitibaogaoService;
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
 * 开题报告
 * 后端接口
 */
@RestController
@RequestMapping("/kaitibaogao")
public class KaitibaogaoController {
    @Autowired
    private KaitibaogaoService kaitibaogaoService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KaitibaogaoEntity kaitibaogao,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			kaitibaogao.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiaoshi")) {
			kaitibaogao.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<KaitibaogaoEntity> ew = new EntityWrapper<KaitibaogaoEntity>();

		PageUtils page = kaitibaogaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaitibaogao), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KaitibaogaoEntity kaitibaogao, 
		HttpServletRequest request){
        EntityWrapper<KaitibaogaoEntity> ew = new EntityWrapper<KaitibaogaoEntity>();

		PageUtils page = kaitibaogaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaitibaogao), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KaitibaogaoEntity kaitibaogao){
       	EntityWrapper<KaitibaogaoEntity> ew = new EntityWrapper<KaitibaogaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kaitibaogao, "kaitibaogao")); 
        return R.ok().put("data", kaitibaogaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KaitibaogaoEntity kaitibaogao){
        EntityWrapper< KaitibaogaoEntity> ew = new EntityWrapper< KaitibaogaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kaitibaogao, "kaitibaogao")); 
		KaitibaogaoView kaitibaogaoView =  kaitibaogaoService.selectView(ew);
		return R.ok("查询开题报告成功").put("data", kaitibaogaoView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KaitibaogaoEntity kaitibaogao = kaitibaogaoService.selectById(id);
        return R.ok().put("data", kaitibaogao);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KaitibaogaoEntity kaitibaogao = kaitibaogaoService.selectById(id);
        return R.ok().put("data", kaitibaogao);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KaitibaogaoEntity kaitibaogao, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kaitibaogao);
        kaitibaogaoService.insert(kaitibaogao);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KaitibaogaoEntity kaitibaogao, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kaitibaogao);
        kaitibaogaoService.insert(kaitibaogao);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KaitibaogaoEntity kaitibaogao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaitibaogao);
        kaitibaogaoService.updateById(kaitibaogao);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kaitibaogaoService.deleteBatchIds(Arrays.asList(ids));
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
                    KaitibaogaoEntity kaitibaogaoEntity =new KaitibaogaoEntity();
                    kaitibaogaoEntity.setId(new Date().getTime());
                     
                    //想数据库中添加新对象
                    kaitibaogaoService.insert(kaitibaogaoEntity);//方法
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
