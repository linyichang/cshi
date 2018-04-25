package com.lin.utils.wx.domain;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Massage {

	private static Map<String, String> xml = new HashMap<String, String>();
	public static Map<String, String> MgsToMap(HttpServletRequest request){
		try {
			InputStream stream = request.getInputStream();
			SAXReader reader = new SAXReader();
			Document read = reader.read(stream);
			Element root = read.getRootElement();
			List<Element> elements = root.elements();
			for (Element el : elements) {
				xml.put(el.getName(), el.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	public static String sendMassage(String toUser,String fromUser,String text){
		
		StringBuffer xml = new StringBuffer();
		xml.append("<xml>");
		xml.append("<ToUserName>"+toUser+"</ToUserName>");
		xml.append("<FromUserName>"+fromUser+"</FromUserName>");
		xml.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
		xml.append("<MsgType>text</MsgType>");
		xml.append("<Content>"+text+"</Content>");
		xml.append("</xml>");
		return xml.toString();
	}

}
