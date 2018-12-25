package com.jt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysConfigDao;
import com.jt.sys.entity.SysConfig;
import com.jt.sys.service.SysConfigService;
@Service
public class SysConfigServiceImpl implements SysConfigService{
	@Autowired
	private SysConfigDao sysConfigDao;
	@Override
	public PageObject<SysConfig> findPageObjects(String name, Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页不存在");
		int rowCount=sysConfigDao.getRowCount(name);
		if(rowCount==0)
			throw new IllegalArgumentException("查找不到记录");
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysConfig> records=sysConfigDao.findPageObjects(name, pageSize, startIndex);
		PageObject<SysConfig> pageObject = new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
		pageObject.setRowCount(rowCount);
		return pageObject;
	}
	@Override
	public int deleteObjects(Integer... ids) {
		if(ids==null||ids.length==0)
			throw new IllegalArgumentException("至少选一个");
		int row;
		try {
			row=sysConfigDao.deleteObjects(ids);
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		throw new ServiceException("系统故障");
		}
		if(row==0)
			throw new ServiceException("记录可能不存在");
		return row;
	}
	@Override
	public int doSaveObjects(SysConfig entity) {
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("参数名不能为空");
		if(StringUtils.isEmpty(entity.getValue()))
			throw new IllegalArgumentException("参数值不能为空");
		int row;
		try {
			row=sysConfigDao.doSaveObjects(entity);
			System.out.println(row);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new ServiceException("系统故障");
		}
		return row;
	}
		@Override
		public int doUpdateObjects(SysConfig entity) {
			if(entity==null)
				throw new IllegalArgumentException("保存对象不能为空");
			if(StringUtils.isEmpty(entity.getName()))
				throw new IllegalArgumentException("参数名不能为空");
			if(StringUtils.isEmpty(entity.getValue()))
				throw new IllegalArgumentException("参数值不能为空");
			int row;
			try {
				row=sysConfigDao.doUpdateObjects(entity);
			} catch (Throwable e) {
				e.printStackTrace();
				throw new ServiceException("系统故障");
			}
			return row;
	}

}
