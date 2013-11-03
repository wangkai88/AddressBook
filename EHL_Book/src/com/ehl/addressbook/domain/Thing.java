package com.ehl.addressbook.domain;

import java.io.Serializable;

/**
 * 用于商品的属性
 * @author Administrator
 *
 */
public class Thing implements Serializable
{
	private static final long serialVersionUID = -4258659479492365071L;
	private String name ="";
	private String url = "";
	private String id = "";
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public Thing()
	{
	 
	}
	public Thing(String id,String name,String url)
	{
		this.id = id;
		this.name= name;
		this.url = url;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	
}
