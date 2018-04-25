package com.lin.sys.common.utils;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ckfinder.connector.ConnectorServlet;
import com.ckfinder.connector.configuration.Configuration;
import com.ckfinder.connector.data.AccessControlLevel;
import com.ckfinder.connector.utils.AccessControlUtil;

/**
 * CKFinder����
 * @author ThinkGem
 * @version 2014-06-25
 */
public class CKFinderConfig extends Configuration {

	public CKFinderConfig(ServletConfig servletConfig) {
        super(servletConfig);  
    }
	
	@Override
    protected Configuration createConfigurationInstance() {
		boolean isView = true;
		boolean isUpload = true;
		boolean isEdit = true;
		AccessControlLevel alc = this.getAccessConrolLevels().get(0);
		alc.setFolderView(isView);
		alc.setFolderCreate(isEdit);
		alc.setFolderRename(isEdit);
		alc.setFolderDelete(isEdit);
		alc.setFileView(isView);
		alc.setFileUpload(isUpload);
		alc.setFileRename(isEdit);
		alc.setFileDelete(isEdit);
		AccessControlUtil.getInstance(this).loadACLConfig();
		try {
			String paths = this.getClass().getClassLoader().getResource("/").getPath().replace("WEB-INF/classes/", "");
			this.baseURL = FileUtils.path(EncdingUTF_8(paths+"uolp/ckfinder"  + Global.USERFILES_BASE_URL + "/"));
			this.baseDir = FileUtils.path(EncdingUTF_8(paths+"uolp/ckfinder"  + Global.USERFILES_BASE_URL + "/"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new CKFinderConfig(this.servletConf);
    }

	public static String EncdingUTF_8(String value){
		String val = null;
		try {
		val = new String(value.getBytes("ISO-8859-1"),"utf-8");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return val;
	}
}
