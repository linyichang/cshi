package com.lin.contorller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.apache.catalina.connector.OutputBuffer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sun.util.calendar.BaseCalendar;

import com.google.gson.Gson;
import com.lin.entity.User;
import com.lin.service.UserService;
import com.lin.sys.common.tag.SysMessage;
import com.lin.sys.entity.SysMenu;
import com.lin.sys.entity.SysRole;
import com.lin.sys.entity.SysRolePerm;
import com.lin.sys.entity.SysRoleUser;
import com.lin.sys.entity.SysUser;
import com.lin.sys.service.SysRolePermService;
import com.lin.sys.service.SysRoleService;
import com.lin.sys.service.SysRoleUserService;
import com.lin.sys.service.SysUserService;
import com.lin.utils.SysPage;
import com.lin.utils.Info.ApiConstance;
import com.lin.utils.Info.BaseController;
import com.lin.utils.Info.BaseResult;
import com.lin.utils.Info.InfoJson;
import com.lin.utils.files.Progress;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import freemarker.template.utility.SecurityUtilities;

@Controller
@RequestMapping(value = "/user/lg")
public class LoginUserContorller extends BaseController {

	Logger logger = LoggerFactory.getLogger(LoginUserContorller.class);

	@Autowired
	private UserService userService;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private SysRolePermService sysRolePermService;

	@Autowired
	private SysRoleUserService sysRoleUserService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return returnPath("sys/login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String Userindex(SysUser user, Model model,RedirectAttributes redirectAttributes) {
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try {
			subject.login(token);
		} catch (Exception e) {
			SysMessage sysMessage = new SysMessage("登录失败，密码或者账号错误！", "error");
			model.addAttribute("message", sysMessage);
			return returnPath("sys/login");
		}
		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		List<SysMenu> menus = sysUserService.findMenu(sysUser.getId());
		model.addAttribute("user", sysUser);
		model.addAttribute("menus", menus);
		addMessage("登录成功！", SUCCESS, redirectAttributes);
		return returnPath("index");
	}
	
	

	@RequestMapping(value = "/files", method = RequestMethod.POST)
	public String springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
		long startTime = System.currentTimeMillis();
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());

				if (file != null) {

					File tempFile = new File(request.getSession().getServletContext().getRealPath(".") + "/" + file.getOriginalFilename());
					//

					// System.out.println("文件大小："+multiRequest.getContentLength());
					request.getSession().setAttribute("dataInfo", multiRequest.getContentLength());
					String path = "c:\\cf\\" + System.currentTimeMillis() + file.getOriginalFilename();
					// 上传
					file.transferTo(new File(path));
					System.out.println(request.getSession().getServletContext().getRealPath("") + "\\" + file.getOriginalFilename());
					System.out.println(tempFile.getAbsolutePath());
				}

			}

		}
		long endTime = System.currentTimeMillis();
		System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
		return returnPath("sys/roleList");
	}

	@RequestMapping(value = "/upfile/progress", method = RequestMethod.GET)
	@ResponseBody
	public String initCreateInfo(HttpServletRequest request) {
		Progress status = (Progress) request.getSession().getAttribute("upload_ps");
		if (status == null) {
			return "{}";
		}
		System.out.println(status);
		return status.toString();
	}
}
