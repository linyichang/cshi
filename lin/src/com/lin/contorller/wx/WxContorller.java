package com.lin.contorller.wx;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.lin.entity.User;
import com.lin.entity.WxUser;
import com.lin.service.UserService;
import com.lin.service.WxUserService;
import com.lin.utils.wx.domain.Massage;
import com.lin.utils.wx.utils.CheckUtils;

public class WxContorller extends HttpServlet {

	

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		if (CheckUtils.CheckSinginUtil(signature, timestamp, nonce)) {
			out.print(echostr);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Map<String, String> msg = Massage.MgsToMap(request);
		String fromUserName = msg.get("FromUserName");
		String toUserName = msg.get("ToUserName");
		String content = msg.get("Content");
		String msgType = msg.get("MsgType");
		String event = msg.get("Event");
		String sendMassage = null;
		PrintWriter out = response.getWriter();
		if ("text".equals(msgType)) {

			if ("时间".contains(content)) {
				msg.put("Content", new Date().toLocaleString());
				sendMassage = CheckUtils.sendInfo(msg);
			} else if ("1".contains(content)) {
				msg.put("Content", "测试服务！");
				sendMassage = CheckUtils.sendInfo(msg);
			} else if ("2".contains(content)) {
				msg.put("Content", "地面！");
				sendMassage = CheckUtils.sendInfo(msg);
			} else if ("3".contains(content)) {
				msg.put("Content", "空中！");
				sendMassage = CheckUtils.sendInfo(msg);
			} else {
				msg.put("Content", "对不起，没有相关服务！");
				sendMassage = CheckUtils.sendInfo(msg);
			}
			out.print(sendMassage);
		} else if ("event".equals(msgType)) {
			if ("subscribe".equals(event)) {
				CheckUtils.getUserInfo(fromUserName);
				String wlk = CheckUtils.sendInfo(msg);		
				String userInfo = CheckUtils.getUserInfo(fromUserName);
				WxUser wx = new Gson().fromJson(userInfo, WxUser.class);
				WxUserService wxUserService = SpringContextHolder.getBean(WxUserService.class);
				WxUser wxUser = new WxUser();
				wxUser.setNickname(wx.getNickname());
				wxUser.setCity(wx.getCity());
				wxUser.setHeadimgurl(wx.getHeadimgurl());
				wxUser.setProvince(wx.getProvince());
				wxUser.setSex(wx.getSex());
				wxUser.setCountry(wx.getCountry());
				wxUser.setOpenid(fromUserName);
				wxUserService.saveUser(wxUser);
				System.out.println("添加用户成功！");
				out.print(wlk);
			} else if ("CLICK".equals(event)) {
				request.getRequestDispatcher("/").forward(request, response);
			}
		}
	}
}
