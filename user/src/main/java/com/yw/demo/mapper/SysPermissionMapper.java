package com.yw.demo.mapper;
import java.util.List;
import com.yw.demo.domain.SysPermission;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * SysPermissionMapper数据库操作接口类
 * 
 **/

public interface SysPermissionMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	SysPermission  selectByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(SysPermission record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(SysPermission record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(SysPermission record);

	/**
	 * 
	 * 分页查询</br>eg: xx.getByPage(null, 0, 3, "id", "desc");
	 * 
	 **/
	List<SysPermission> getByPage(@Param("sysPermission") SysPermission sysPermission, @Param("start") Integer start, @Param("limit") Integer limit, @Param("orderColumn") String orderColumn, @Param("orderType") String orderType);

	/**
	 * 
	 * 根据实体属性查询
	 * 
	 **/
	List<SysPermission> getListByCondition(@Param("sysPermission") SysPermission sysPermission);

	/**
	 * 
	 * 根据实体属性查询
	 * 
	 **/
	int getCountByCondition(@Param("sysPermission") SysPermission sysPermission);

	/**
	 * 
	 * 查询单条数据
	 * 
	 **/
	SysPermission getOneByCondition(@Param("sysPermission") SysPermission sysPermission);


	List<SysPermission> getList(int userId);

}