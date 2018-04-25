package com.lin.utils.Info;

import java.util.HashMap;
import java.util.Map;


public class ApiConstance {
	
	public static Map<Integer, String>map = new HashMap<Integer, String>();
	
	public String getState(Integer state){
		return map.get(state);
	}
	/**
	 * 成功
	 */
	public static final int SUSSECC = 200;
	
	/**
	 * 成功
	 */
	public static final int NO_SUSSECC = 400;
	
	static{
		map.put(SUSSECC, "success");
		map.put(NO_SUSSECC, "unSuccess");
	}
}
