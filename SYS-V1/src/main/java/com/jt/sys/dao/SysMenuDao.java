package com.jt.sys.dao;

import java.util.List;
import java.util.Map;

import com.jt.common.vo.Node;
import com.jt.sys.entity.SysMenu;

public interface SysMenuDao {
	List<Map<String,Object>> findObjects();
	int getChildCount(Integer id);
	int deleteObject(Integer id);
	List<Node> findZtreeMenuNode();
	int insertObject(SysMenu entity);
	int updateObject(SysMenu entity);
}
