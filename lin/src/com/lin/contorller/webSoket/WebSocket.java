package com.lin.contorller.webSoket;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocket {

	private Session session;
	
	private static int i = 0;
	
//	@OnOpen
//	public void Session(Session session) throws Exception{
//		this.session = session;
//		Cache.putSession(key, object)
//		System.out.println("�������Ӽ��룡"+i);
//	}


}
