package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.sys.entity.SysMenu;
import com.jt.sys.service.SysMenuService;

@Controller
@RequestMapping("/menu/")
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;
	@RequestMapping("doMenuListUI")
	public String doMenuListUI() {
		return "sys/menu_list";
	}
	@RequestMapping("doMenuEditUI")
	public String doMenuEditUI() {
		return "sys/menu_edit";
	}
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult findObjects() {
		return new JsonResult(sysMenuService.findObjects());
	}
	
	@RequestMapping("doDeleteObject/{id}")
	@ResponseBody
	public JsonResult doDeleteObject(@PathVariable Integer id) {
		sysMenuService.deleteObject(id);
		return new JsonResult("delete ok");
				
	}
	@RequestMapping("doFindZtreeMenuNodes")
	@ResponseBody
	public JsonResult doFindZtreeMenuNodes() {
		return new JsonResult(sysMenuService.findZtreeMenuNode());
	}
	@RequestMapping(value="doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysMenu entity) {
		sysMenuService.saveObject(entity);
		return new JsonResult("save ok");
	}
	@RequestMapping(value="doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysMenu entity) {
		sysMenuService.updateObject(entity);
		return new JsonResult("update ok");
	}
}
