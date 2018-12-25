package com.jt.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.exception.ServiceException;
import com.jt.common.vo.Node;
import com.jt.sys.dao.SysMenuDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.entity.SysMenu;
import com.jt.sys.service.SysMenuService;
@Service
public class SysMenuServiceImpl implements SysMenuService{
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Override
	public List<Map<String, Object>> findObjects() {
		return sysMenuDao.findObjects();
	}
	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<0)
			throw new IllegalArgumentException("至少选一个");
		int count=sysMenuDao.getChildCount(id);
		if(count>0)
			throw new IllegalArgumentException("先删除子菜单");
		int row=sysMenuDao.deleteObject(id);
		if(row==0)
			throw new ServiceException("记录可能不存在了");
			sysRoleMenuDao.deleteObjectsByMenuId(id);
		return row;
	}
	@Override
	public List<Node> findZtreeMenuNode() {
		return sysMenuDao.findZtreeMenuNode();
	}
	@Override
	public int saveObject(SysMenu entity) {
		if(entity==null)
			throw new IllegalArgumentException("保存内容不可为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("菜单名不能为空");
		int rows=sysMenuDao.insertObject(entity);
		System.out.println(rows);
		if(rows==0)
			throw new ServiceException("save error");
		return rows;
	}
	@Override
	public int updateObject(SysMenu entity) {
		if(entity==null)
			throw new IllegalArgumentException("保存内容不可为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("菜单名不能为空");
		int rows=sysMenuDao.insertObject(entity);
		System.out.println(rows);
		if(rows==0)
			throw new ServiceException("save error");
		return rows;
	}
	
}
