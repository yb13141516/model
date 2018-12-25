package com.jt.sys.service;

import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysRole;

public interface SysRoleService {
	PageObject<SysRole> findPageObjects(String name,Integer pageCurrent);
	int deleteObject(Integer id);
	int saveObject(SysRole entity,Integer[] menuIds);
}
