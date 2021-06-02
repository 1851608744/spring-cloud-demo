package com.yw.demo.mapper;
import java.util.List;
import com.yw.demo.domain.SysRole;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * SysRoleMapper数据库操作接口类
 * 
 **/

public interface SysRoleMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	SysRole  selectByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(SysRole record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(SysRole record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(SysRole record);

	/**
	 * 
	 * 分页查询</br>eg: xx.getByPage(null, 0, 3, "id", "desc");
	 * 
	 **/
	List<SysRole> getByPage(@Param("sysRole") SysRole sysRole, @Param("start") Integer start, @Param("limit") Integer limit, @Param("orderColumn") String orderColumn, @Param("orderType") String orderType);

	/**
	 * 
	 * 根据实体属性查询
	 * 
	 **/
	List<SysRole> getListByCondition(@Param("sysRole") SysRole sysRole);

	/**
	 * 
	 * 根据实体属性查询
	 * 
	 **/
	int getCountByCondition(@Param("sysRole") SysRole sysRole);

	/**
	 * 
	 * 查询单条数据
	 * 
	 **/
	SysRole getOneByCondition(@Param("sysRole") SysRole sysRole);

}