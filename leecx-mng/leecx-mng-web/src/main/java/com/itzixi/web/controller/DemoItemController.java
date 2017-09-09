package com.itzixi.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itzixi.common.pojo.JqGridResult;
import com.itzixi.common.utils.LeeJSONResult;
import com.itzixi.pojo.DemoItem;
import com.itzixi.pojo.SysUser;
import com.itzixi.service.DemoItemService;

/**
 * 
 * @Title: UserController.java
 * @Package com.itzixi.web.controller
 * @Description: 用户 controller
 * Copyright: Copyright (c) 2016
 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
 * 
 * @author leechenxiang
 * @date 2017年9月5日 下午2:10:15
 * @version V1.0
 */
@Controller
@RequestMapping("/demoItem")
public class DemoItemController extends BaseController {
	
	final static Logger log = LoggerFactory.getLogger(DemoItemController.class);
	
	@Autowired
	private DemoItemService itemService;
	
	/**
	 * 
	 * @Title: DemoItemController.java
	 * @Package com.itzixi.web.controller
	 * @Description: 打开新增商品的页面
	 * Copyright: Copyright (c) 2017
	 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
	 * 
	 * @author leechenxiang
	 * @date 2017年9月8日 下午9:42:20
	 * @version V1.0
	 */
	@RequestMapping("/showCreateItemPage")
	public String showCreateUserPage(HttpServletRequest request){
		
		return "item/createItem";
	}
	
	/**
	 * 
	 * @Description: 创建一个商品或者修改他
	 * @param user
	 * @return
	 * 
	 * @author leechenxiang
	 * @date 2017年9月5日 下午4:39:32
	 */
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public LeeJSONResult saveOrUpdate(DemoItem item){
		
		// 商品id不为空，则修改商品；商品id为空，则新建一个商品
		String itemId = item.getId();
		if (StringUtils.isEmpty(itemId)) {
			
			// 保存商品的操作
			itemService.saveItem(item);
		} else {
//			itemService.updateItemById(item);
		}
		
		return LeeJSONResult.ok();
	}
	
	/**
	 * 
	 * @Title: DemoItemController.java
	 * @Package com.itzixi.web.controller
	 * @Description: 查询商品页面
	 * Copyright: Copyright (c) 2017
	 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
	 * 
	 * @author leechenxiang
	 * @date 2017年9月9日 下午9:07:48
	 * @version V1.0
	 */
	@RequestMapping("/showQueryItemPage")
	public String showQueryItemPage(HttpServletRequest request){
		
		return "item/demoItemList";
	}
	
	/**
	 * 
	 * @Title: DemoItemController.java
	 * @Package com.itzixi.web.controller
	 * @Description: 分页查询商品列表
	 * Copyright: Copyright (c) 2017
	 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
	 * 
	 * @author leechenxiang
	 * @date 2017年9月9日 下午8:37:34
	 * @version V1.0
	 */
	@RequestMapping("/getItemInfoList")
	@ResponseBody
	public JqGridResult getItemInfoList(Integer page){
		
		if (page == null) {
			page = 1;
		}
		
		JqGridResult jqgridResult = itemService.queryItemList(page, pageSize);
		
		return jqgridResult;
	}

}
