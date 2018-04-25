package com.lin.utils.Info;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lin.sys.common.tag.SysMessage;


public class BaseController<T> {

	public static String SUCCESS = "success";
	
	public static String ERROR = "error";
	
	/**
	 * 项目地址
	 */
	public static String PROJECT_PATH = "/WEB-INF/views/";
	
	public static String returnPath(String path){
		StringBuffer p = new StringBuffer();
		p.append(PROJECT_PATH);
		p.append(path);
		p.append(".jsp");
		return p.toString();
	}
	
	protected ResponseEntity<BaseResult> buildSuccessResultInfo(List<T> data) {
		BaseResult result = new BaseResult(0, "success", data.size(), data);
		return new ResponseEntity<BaseResult>(result, HttpStatus.OK);
	}
	
	protected ResponseEntity<Object> buildSuccessResultInfo1(Object resultData){
		return new ResponseEntity<Object>(resultData, HttpStatus.OK);
	}
	
	protected ResponseEntity<Object> buildSuccessResultInfo1(Object resultData,Object obj){
		return new ResponseEntity<Object>(resultData, HttpStatus.OK);
	}
	
	/**
	 * 重定向返回系统提示信息
	 * @param message 提示内容
	 * @param redirectAttributes 重定向属性对象
	 * @param type ：success 成功信息，error 错误信息
	 */
	protected void addMessage(String message, String type, RedirectAttributes redirectAttributes) {
		SysMessage sysMessage = new SysMessage(message, type);
		redirectAttributes.addFlashAttribute("message", sysMessage);
	}
	
}
