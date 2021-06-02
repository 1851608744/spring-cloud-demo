package com.yw.demo.mapper;
import java.util.List;


import com.yw.demo.domain.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * SysUserMapper数据库操作接口类
 * 
 **/

public interface SysUserMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	SysUser  selectByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(SysUser record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(SysUser record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(SysUser record);

	/**
	 * 
	 * 分页查询</br>eg: xx.getByPage(null, 0, 3, "id", "desc");
	 * 
	 **/
	List<SysUser> getByPage(@Param("sysUser") SysUser sysUser, @Param("start") Integer start, @Param("limit") Integer limit, @Param("orderColumn") String orderColumn, @Param("orderType") String orderType);

	/**
	 * 
	 * 根据实体属性查询
	 * 
	 **/
	List<SysUser> getListByCondition(@Param("sysUser") SysUser sysUser);

	/**
	 * 
	 * 根据实体属性查询
	 * 
	 **/
	int getCountByCondition(@Param("sysUser") SysUser sysUser);

	/**
	 * 
	 * 查询单条数据
	 * 
	 **/
	SysUser getOneByCondition(@Param("sysUser") SysUser sysUser);


	/**
	 * 单条查询
	 * @param sysUser
	 * @return
	 */
	SysUser getOne(@Param("sysUser") SysUser sysUser);

}