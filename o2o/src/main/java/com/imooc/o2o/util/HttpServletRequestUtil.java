package com.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
	// 从request提取key， 然后转为int
	public static int getInt(HttpServletRequest request, String key){
		try{
			return Integer.decode(request.getParameter(key));
		}catch(Exception e){
			return -1;
		}
	}	
	// 从request提取key， 然后转为long
	public static long getLong(HttpServletRequest request, String key){
			try{
				return Long.valueOf(request.getParameter(key));
			}catch(Exception e){
				return -1;
			}
		}
	// 从request提取key， 然后转为double
	public static Double getDouble(HttpServletRequest request, String key){
			try{
				return Double.valueOf(request.getParameter(key));
			}catch(Exception e){
				return -1d;
			}
		}	
	// 从request提取key， 然后转为boolean
	public static boolean getBoolean(HttpServletRequest request, String key){
		try{
			return Boolean.valueOf(request.getParameter(key));
		}catch(Exception e){
			return false;
		}
	}	
	//从request提取key， 然后转为String
	public static String getString(HttpServletRequest request, String key){
		try{
			String result = request.getParameter(key);
			if(result != null){
				result = result.trim();
			}
			if("".equals(result)){
				result = null;
			}
			return result;
		} catch(Exception e){
			return null;
		}
		
	}
	
}
