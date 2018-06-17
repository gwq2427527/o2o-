package com.imooc.o2o.util;

public class PageCalculator {
	public static int calculateRowIndex(int pageIndex,int pageSize) {
		//如果pageIndex>0 返回pageIndex-1*pageSize,反之，返回0
		//如果页码pageIndex值为1，表示选择第一页的数据，如果pageSize为5，一页有5条，需要从第0条（rowIndex）开始去选择5条数据，
		//如果pageIndex=2，那么第二页的数据 （2-1）*5
		return (pageIndex >0)?(pageIndex-1)*pageSize:0;
	}
}
