package com.jt.sys.dao;

import org.apache.ibatis.annotations.Param;

public interface SysRoleMenuDao {
	int deleteObjectsByMenuId(Integer menuId);
	int deleteObjectsByRoleId(Integer roleId);
	int insertObject(@Param("roleId")Integer roleId,@Param("menuIds")Integer[] menuIds);
}
