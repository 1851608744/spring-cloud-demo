package com.yw.demo.mapper;
import java.util.List;
import com.yw.demo.domain.SysPermissionRole;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * SysPermissionRoleMapper数据库操作接口类
 * 
 **/

public interface SysPermissionRoleMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	SysPermissionRole  selectByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(SysPermissionRole record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(SysPermissionRole record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(SysPermissionRole record);

	/**
	 * 
	 * 分页查询</br>eg: xx.getByPage(null, 0, 3, "id", "desc");
	 * 
	 **/
	List<SysPermissionRole> getByPage(@Param("sysPermissionRole") SysPermissionRole sysPermissionRole, @Param("start") Integer start, @Param("limit") Integer limit, @Param("orderColumn") String orderColumn, @Param("orderType") String orderType);

	/**
	 * 
	 * 根据实体属性查询
	 * 
	 **/
	List<SysPermissionRole> getListByCondition(@Param("sysPermissionRole") SysPermissionRole sysPermissionRole);

	/**
	 * 
	 * 根据实体属性查询
	 * 
	 **/
	int getCountByCondition(@Param("sysPermissionRole") SysPermissionRole sysPermissionRole);

	/**
	 * 
	 * 查询单条数据
	 * 
	 **/
	SysPermissionRole getOneByCondition(@Param("sysPermissionRole") SysPermissionRole sysPermissionRole);

}