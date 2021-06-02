package com.yw.demo.mapper;
import java.util.List;
import com.yw.demo.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * UserMapper数据库操作接口类
 * 
 **/

public interface UserMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	User  selectByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(User record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(User record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(User record);

	/**
	 * 
	 * 分页查询</br>eg: xx.getByPage(null, 0, 3, "id", "desc");
	 * 
	 **/
	List<User> getByPage(@Param("user") User user, @Param("start") Integer start, @Param("limit") Integer limit, @Param("orderColumn") String orderColumn, @Param("orderType") String orderType);

	/**
	 * 
	 * 根据实体属性查询
	 * 
	 **/
	List<User> getListByCondition(@Param("user") User user);

	/**
	 * 
	 * 根据实体属性查询
	 * 
	 **/
	int getCountByCondition(@Param("user") User user);

	/**
	 * 
	 * 查询单条数据
	 * 
	 **/
	User getOneByCondition(@Param("user") User user);


	User getByAccountAndPassword(@Param("user") User user);

}