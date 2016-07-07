package com.xm2013.tools;

import java.lang.reflect.Field;

public class ReflectTool {
	
	/**
	 * 得到getter方法
	 * @param field
	 * @return
	 */
	public static String getter(Field field){
		String get = "get";
		
		if(field.getType() == boolean.class){
			get="is";
		}
		String fieldName = field.getName();
		get = get+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
		return get;
	}
	
	/**
	 * 得到setter方法
	 * @param field
	 * @return
	 */
	public static String setter(Field field){
		return "set"+field.getName().substring(0, 1)+field.getName().substring(1);
	}
	
	
	
}
