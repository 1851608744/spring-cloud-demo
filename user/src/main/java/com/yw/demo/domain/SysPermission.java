package com.yw.demo.domain;
import java.io.Serializable;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class SysPermission implements Serializable {

	/****/
	private Integer id;

	/****/
	private String name;

	/****/
	private String descritpion;

	/****/
	private String url;

	/****/
	private Integer pid;



	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setDescritpion(String descritpion){
		this.descritpion = descritpion;
	}

	public String getDescritpion(){
		return this.descritpion;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return this.url;
	}

	public void setPid(Integer pid){
		this.pid = pid;
	}

	public Integer getPid(){
		return this.pid;
	}

}
