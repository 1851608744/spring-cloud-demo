package com.yw.demo.domain;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
@ApiModel("用户信息类")
@Data
public class User implements Serializable {

	/**主键**/
	@ApiModelProperty(value = "主键ID")
	private Integer id;

	/**账号**/
	@ApiModelProperty(value = "用户账号")
	private String userAccount;

	/**密码**/
	@ApiModelProperty(value = "用户密码")
	private String userPassword;

	/**用户名**/
	@ApiModelProperty(value = "用户名")
	private String userName;

	/**用户年龄**/
	@ApiModelProperty(value = "用户年龄")
	private Integer userAge;

	/**住址**/
	@ApiModelProperty(value = "地址")
	private String userAddr;

	/**修改时间**/
	@ApiModelProperty(value = "修改时间")
	private java.util.Date updateTime;

	/**创建时间**/
	@ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;

	@ApiModelProperty(value = "删除表示")
	private Integer delFlag;

	@ApiModelProperty(value = "用户角色")
	private String role;

}
