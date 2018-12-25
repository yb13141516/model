package com.jt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysRoleDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.entity.SysRole;
import com.jt.sys.service.SysRoleService;
@Service
public class SysRoleServiceImpl implements SysRoleService{
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页不存在");
		int rowCount=sysRoleDao.getRowCount(name);
		if(rowCount==0)
			throw new IllegalArgumentException("查找不到记录");
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysRole> records=sysRoleDao.findPageObjects(name, startIndex, pageSize);
		PageObject<SysRole> pageObject= new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
		pageObject.setRowCount(rowCount);
		return pageObject;
	}
	@Override
	public int deleteObject(Integer id) {
		if (id==null||id<1)
			throw new IllegalArgumentException("至少选一个");
		int row=sysRoleDao.deleteObejects(id);
		if(row==0)
			throw new ServiceException("记录可能不存在了");
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		sysUserRoleDao.deleteObjectsByRoleId(id);
		return row;
	}
	@Override
	public int saveObject(SysRole entity, Integer[] menuIds) {
		if(entity==null)
			throw new ServiceException("保存数据不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("保存名不能为空");
		if(menuIds.length==0||menuIds==null)
			throw new ServiceException("必须赋权");
		int row = sysRoleDao.insertObject(entity);
		sysRoleMenuDao.insertObject(entity.getId(), menuIds);
		return row;
	}

}
