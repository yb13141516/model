package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entity.SysConfig;

public interface SysConfigDao {
	List<SysConfig> findPageObjects(@Param("name")String name,
			@Param("pageSize")Integer pageSize,
			@Param("startIndex")Integer startIndex);
	int getRowCount(@Param("name")String name);
	int deleteObjects(@Param("ids")Integer... ids);
	int doSaveObjects(SysConfig entity);
	int doUpdateObjects(SysConfig entity);
}
