package com.yw.demo.domain;
import java.io.Serializable;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class SysPermissionRole implements Serializable {

	/****/
	private Integer id;

	/****/
	private Integer roleId;

	/****/
	private Integer permissionId;



	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}

	public Integer getRoleId(){
		return this.roleId;
	}

	public void setPermissionId(Integer permissionId){
		this.permissionId = permissionId;
	}

	public Integer getPermissionId(){
		return this.permissionId;
	}

}
