package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysConfig;
import com.jt.sys.service.SysConfigService;

@Controller
@RequestMapping("/config/")
public class SysConfigController {
	@Autowired
	private SysConfigService sysConfigService;
	@RequestMapping("doConfigListUI")
	public String doConfigListUI() {
		return "sys/config_list";
	}
	@RequestMapping("doshowEditObjects")
	public String doshowEditObjects() {
		return "sys/config_edit";
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name,Integer pageCurrent) {
		PageObject<SysConfig> pageObject =sysConfigService.findPageObjects(name, pageCurrent);
		return new JsonResult(pageObject);
	}
	@RequestMapping(value="doDeleteObjects/{ids}",produces="application/json;charset=utf-8")
	@ResponseBody
	public JsonResult doDeleteObjects(@PathVariable Integer... ids) {
		sysConfigService.deleteObjects(ids);
		return new JsonResult("delete OK");
	}
	@RequestMapping(value="doSaveObjects",produces="application/json;charset=utf-8")
	@ResponseBody
	public JsonResult doSaveObjects(@RequestBody SysConfig entity) {
		sysConfigService.doSaveObjects(entity);
		return new JsonResult("save ok");
	}
	@RequestMapping(value="doUpdateObjects",produces="application/json;charset=utf-8")
	@ResponseBody
	public JsonResult doUpdateObjects(@RequestBody SysConfig entity) {
		sysConfigService.doUpdateObjects(entity);
		return new JsonResult("update ok");
	}
}
