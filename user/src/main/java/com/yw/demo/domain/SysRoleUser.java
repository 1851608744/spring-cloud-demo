package com.yw.demo.domain;
import java.io.Serializable;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class SysRoleUser implements Serializable {

	/****/
	private Integer id;

	/****/
	private Integer sysUserId;

	/****/
	private Integer sysRoleId;



	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setSysUserId(Integer sysUserId){
		this.sysUserId = sysUserId;
	}

	public Integer getSysUserId(){
		return this.sysUserId;
	}

	public void setSysRoleId(Integer sysRoleId){
		this.sysRoleId = sysRoleId;
	}

	public Integer getSysRoleId(){
		return this.sysRoleId;
	}

}
