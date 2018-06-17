package com.imooc.o2o.entity;


import java.sql.Date;

public class Area {
	//成员变量
	/*
	 *  1、Integer是int的包装类，int则是java的一种基本数据类型 
		2、Integer变量必须实例化后才能使用，而int变量不需要 
		3、Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象；而int则是直接存储数据值 
		4、Integer的默认值是null，int的默认值是0
	 * */
	//ID
	private Integer areaId;
	//区域名
	private String areaName;
	//权重/优先级
	private Integer priority;
	//创建时间
	private Date createTime;
	//更新时间
	private Date lastEditTime;
	
	
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

}
