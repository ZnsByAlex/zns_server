package com.zns.app.util;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class BeanUtil {

	 public static Map<String, String> toMap(Object javaBean) 
	    { 
	        Map<String, String> result = new LinkedHashMap<String, String>(); 
	        Method[] methods = javaBean.getClass().getDeclaredMethods(); 

	        for (Method method : methods) 
	        { 
	            try 
	            { 
	                if (method.getName().startsWith("get")) 
	                { 
	                    String field = method.getName(); 
	                    field = field.substring(field.indexOf("get") + 3); 
	                    field = field.toLowerCase().charAt(0) + field.substring(1); 

	                    Object value = method.invoke(javaBean, (Object[])null); 
	                    result.put(field, null == value ? "" : value.toString()); 
	                } 
	            } 
	            catch (Exception e) 
	            { 
	            	e.printStackTrace();
	            } 
	        } 

	        return result; 
	    } 
}
