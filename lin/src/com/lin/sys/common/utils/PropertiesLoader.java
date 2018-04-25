//package com.lin.servlet;
//
///**
// * Copyright (c) 2005-2011 springside.org.cn
// * 
// * $Id: PropertiesLoader.java 1690 2012-02-22 13:42:00Z calvinxiu $
// */
//
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.NoSuchElementException;
//import java.util.Properties;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.naming.resources.Resource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
///**
// * Properties�ļ����빤����. ��������properties�ļ�, ��ͬ�����������������ļ��е�ֵ���Ḳ��֮ǰ��ֵ������System��Property����.
// * @author calvin
// * @version 2013-05-15
// */
//public class PropertiesLoader {
//
//	private static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);
//
////	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
////
////	private final Properties properties;
////
////	public PropertiesLoader(String... resourcesPaths) {
////		properties = loadProperties(resourcesPaths);
////	}
////
////	public Properties getProperties() {
////		return properties;
////	}
//
//	/**
//	 * ȡ��Property������System��Property����,ȡ�������ؿ��ַ���.
//	 */
//	private String getValue(String key) {
//		String systemProperty = System.getProperty(key);
//		if (systemProperty != null) {
//			return systemProperty;
//		}
//		if (properties.containsKey(key)) {
//	        return properties.getProperty(key);
//	    }
//	    return "";
//	}
//
//	/**
//	 * ȡ��String���͵�Property������System��Property����,�����ΪNull���׳��쳣.
//	 */
//	public String getProperty(String key) {
//		String value = getValue(key);
//		if (value == null) {
//			throw new NoSuchElementException();
//		}
//		return value;
//	}
//
//	/**
//	 * ȡ��String���͵�Property������System��Property����.�����ΪNull�򷵻�Defaultֵ.
//	 */
//	public String getProperty(String key, String defaultValue) {
//		String value = getValue(key);
//		return value != null ? value : defaultValue;
//	}
//
//	/**
//	 * ȡ��Integer���͵�Property������System��Property����.�����ΪNull�����ݴ������׳��쳣.
//	 */
//	public Integer getInteger(String key) {
//		String value = getValue(key);
//		if (value == null) {
//			throw new NoSuchElementException();
//		}
//		return Integer.valueOf(value);
//	}
//
//	/**
//	 * ȡ��Integer���͵�Property������System��Property����.�����ΪNull�򷵻�Defaultֵ��������ݴ������׳��쳣
//	 */
//	public Integer getInteger(String key, Integer defaultValue) {
//		String value = getValue(key);
//		return value != null ? Integer.valueOf(value) : defaultValue;
//	}
//
//	/**
//	 * ȡ��Double���͵�Property������System��Property����.�����ΪNull�����ݴ������׳��쳣.
//	 */
//	public Double getDouble(String key) {
//		String value = getValue(key);
//		if (value == null) {
//			throw new NoSuchElementException();
//		}
//		return Double.valueOf(value);
//	}
//
//	/**
//	 * ȡ��Double���͵�Property������System��Property����.�����ΪNull�򷵻�Defaultֵ��������ݴ������׳��쳣
//	 */
//	public Double getDouble(String key, Integer defaultValue) {
//		String value = getValue(key);
//		return value != null ? Double.valueOf(value) : defaultValue;
//	}
//
//	/**
//	 * ȡ��Boolean���͵�Property������System��Property����.�����ΪNull�׳��쳣,������ݲ���true/false�򷵻�false.
//	 */
//	public Boolean getBoolean(String key) {
//		String value = getValue(key);
//		if (value == null) {
//			throw new NoSuchElementException();
//		}
//		return Boolean.valueOf(value);
//	}
//
//	/**
//	 * ȡ��Boolean���͵�Property������System��Property����.�����ΪNull�򷵻�Defaultֵ,������ݲ�Ϊtrue/false�򷵻�false.
//	 */
//	public Boolean getBoolean(String key, boolean defaultValue) {
//		String value = getValue(key);
//		return value != null ? Boolean.valueOf(value) : defaultValue;
//	}
//
//}
