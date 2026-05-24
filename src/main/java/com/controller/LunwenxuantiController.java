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

import com.entity.LunwenxuantiEntity;
import com.entity.view.LunwenxuantiView;

import com.service.LunwenxuantiService;
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
 * 论文选题
 * 后端接口
 */
@RestController
@RequestMapping("/lunwenxuanti")
public class LunwenxuantiController {
    @Autowired
    private LunwenxuantiService lunwenxuantiService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,LunwenxuantiEntity lunwenxuanti,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			lunwenxuanti.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiaoshi")) {
			lunwenxuanti.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<LunwenxuantiEntity> ew = new EntityWrapper<LunwenxuantiEntity>();

		PageUtils page = lunwenxuantiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lunwenxuanti), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,LunwenxuantiEntity lunwenxuanti, 
		HttpServletRequest request){
        EntityWrapper<LunwenxuantiEntity> ew = new EntityWrapper<LunwenxuantiEntity>();

		PageUtils page = lunwenxuantiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lunwenxuanti), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( LunwenxuantiEntity lunwenxuanti){
       	EntityWrapper<LunwenxuantiEntity> ew = new EntityWrapper<LunwenxuantiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( lunwenxuanti, "lunwenxuanti")); 
        return R.ok().put("data", lunwenxuantiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(LunwenxuantiEntity lunwenxuanti){
        EntityWrapper< LunwenxuantiEntity> ew = new EntityWrapper< LunwenxuantiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( lunwenxuanti, "lunwenxuanti")); 
		LunwenxuantiView lunwenxuantiView =  lunwenxuantiService.selectView(ew);
		return R.ok("查询论文选题成功").put("data", lunwenxuantiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        LunwenxuantiEntity lunwenxuanti = lunwenxuantiService.selectById(id);
        return R.ok().put("data", lunwenxuanti);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        LunwenxuantiEntity lunwenxuanti = lunwenxuantiService.selectById(id);
        return R.ok().put("data", lunwenxuanti);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LunwenxuantiEntity lunwenxuanti, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(lunwenxuanti);
        lunwenxuantiService.insert(lunwenxuanti);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody LunwenxuantiEntity lunwenxuanti, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(lunwenxuanti);
        lunwenxuantiService.insert(lunwenxuanti);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody LunwenxuantiEntity lunwenxuanti, HttpServletRequest request){
        //ValidatorUtils.validateEntity(lunwenxuanti);
        lunwenxuantiService.updateById(lunwenxuanti);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<LunwenxuantiEntity> list = new ArrayList<LunwenxuantiEntity>();
        for(Long id : ids) {
            LunwenxuantiEntity lunwenxuanti = lunwenxuantiService.selectById(id);
            lunwenxuanti.setSfsh(sfsh);
            lunwenxuanti.setShhf(shhf);
            list.add(lunwenxuanti);
        }
        lunwenxuantiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        lunwenxuantiService.deleteBatchIds(Arrays.asList(ids));
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
                    LunwenxuantiEntity lunwenxuantiEntity =new LunwenxuantiEntity();
                    lunwenxuantiEntity.setId(new Date().getTime());
                     
                    //想数据库中添加新对象
                    lunwenxuantiService.insert(lunwenxuantiEntity);//方法
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
