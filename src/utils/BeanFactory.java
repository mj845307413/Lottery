package utils;

import java.io.IOException;
import java.util.Properties;

import engine.UserEngine;

public class BeanFactory {
	private static Properties properties;
	static{
		properties=new Properties();
		try {
			properties.load(BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
public static<T>T getImpl(Class<T> clazz) {
	String key=clazz.getSimpleName();
	String classname=properties.getProperty(key);
	try {
		return (T)Class.forName(classname).newInstance();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}
}
