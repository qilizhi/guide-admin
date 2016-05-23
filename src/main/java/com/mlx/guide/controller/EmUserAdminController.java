package com.mlx.guide.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.EmUser;
import com.mlx.guide.service.EmUserService;

@Controller
@RequestMapping(value="/admin/emUser")
public class EmUserAdminController {
	
	Logger logger =LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EmUserService emUserService;

	
	/**
	 * select 列表
	 * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
	public JsonResult selectList(EmUser emUser){
		List<EmUser> list=new ArrayList<EmUser>();
		try {
			list = emUserService.listByExample(emUser);
		} catch (Exception e) {
			e.printStackTrace();
            logger.info(e.getMessage());
			return new JsonResult(ExceptionCode.FAIL,e.getMessage());
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL,list);
	}

	

}
