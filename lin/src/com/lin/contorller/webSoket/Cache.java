package com.lin.contorller.webSoket;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.apache.catalina.core.ApplicationContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jdt.internal.compiler.ast.ContinueStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;




import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Cache {

	private static Map<String, Session> map = new HashMap<String, Session>();
	
	/**
	 * 添加一个session
	 * @param key
	 * @param object
	 * @return
	 */
	public static Map<String, Session> putSession(String key,Session object){
		map.put(key, object);
		return map;
	}
	
	/**
	 * 获取一个session
	 * @param key
	 * @return
	 */
	public static Session getSessiob(String key){
		return map.get(key);
	}
	
	
	/**
	 * 删除一个session
	 * @param key
	 */
	public static void removeSession(String key){
		map.remove(key);
	}
	
}
