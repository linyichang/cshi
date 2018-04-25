package com.lin.sys.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.util.UriUtils;

import com.ckfinder.connector.ConnectorServlet;
import com.ckfinder.connector.ServletContextFactory;

public class CKFinderConnectorServlet extends ConnectorServlet  {
	
private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		prepareGetResponse(request, response, false);
		super.doGet(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		prepareGetResponse(request, response, true);
		super.doPost(request, response);
	}
	private void prepareGetResponse(final HttpServletRequest request,
			final HttpServletResponse response, final boolean post) throws ServletException {

		String command = request.getParameter("command");
		String type = request.getParameter("type");
		if ("Init".equals(command)){
			String startupPath = request.getParameter("startupPath");// ��ǰ�ļ��п�ָ��Ϊģ����
			if (startupPath!=null){
				String[] ss = startupPath.split(":");
				if (ss.length==2){
					//String realPath = Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL+ principal + "/" + ss[0] + ss[1];
					String baseURL = FileUtils.path("c:/ckfinder/ss"  + Global.USERFILES_BASE_URL + "/");
					FileUtils.createDirectory(FileUtils.path(baseURL));
				}
			}
		}
		else if ("QuickUpload".equals(command) && type!=null){
			String currentFolder = request.getParameter("currentFolder");// ��ǰ�ļ��п�ָ��Ϊģ����
			String baseURL = FileUtils.path("ckfinder"  + Global.USERFILES_BASE_URL + "/");
			FileUtils.createDirectory(FileUtils.path(baseURL));
		}
	}
}
