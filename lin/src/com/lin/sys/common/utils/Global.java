package com.lin.sys.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Global {

	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = new HashMap();
	private static Global global = new Global();

	/**
	 * 属性文件加载对象
	 */
	// private static PropertiesLoader loader = new
	// PropertiesLoader("jeesite.properties");

	public static final String USERFILES_BASE_URL = "/userfiles/";

	public static Global getInstance() {
		return global;
	}

	public static Object getConst(String field) {
		try {
			return Global.class.getField(field).get(null);
		} catch (Exception e) {

			return null;

		}
	}

}
