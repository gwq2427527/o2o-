package com.imooc.o2o.enums;

public enum ShopStateEnum {
	/**
	 * 构造器为私有的，防止外都程序改变值，只能通过内部使用
	 */
	CHECK(0,"审核中"), OFFLINE(-1,"非法店铺"), SUCCESS(1,"操作成功"), 
	PASS(2,"通过认证"), INNER_ERROR(-1001,"内部系统错误"), NULL_SHOPID(-1002,"shop id 为空"),
	NULL_SHOP(-1003,"SHOP信息为空");
	
	private int state;
	private String stateInfo;
	
	private ShopStateEnum(int state, String stateInfo){
		this.state = state;
		this.stateInfo = stateInfo;
		
	}
	
	/**
	 * 依据传入的state返回相应的enum值
	 */
	public static ShopStateEnum stateOf(int state){
		for(ShopStateEnum stateEnum : values()){
			if(stateEnum.getState() == state){
				return stateEnum;
			}
		}
		return null;
		
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	
	
	
}
