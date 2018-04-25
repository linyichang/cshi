package com.lin.contorller.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.util.calendar.BaseCalendar;

import com.google.gson.Gson;
import com.lin.sys.entity.SysMenu;
import com.lin.sys.entity.SysUser;
import com.lin.entity.User;
import com.lin.entity.ZtreeBean;
import com.lin.sys.service.SysMenuService;
import com.lin.sys.service.SysUserService;
import com.lin.sys.utils.Tool;
import com.lin.service.UserService;
import com.lin.utils.Info.ApiConstance;
import com.lin.utils.Info.BaseController;
import com.lin.utils.Info.BaseResult;
import com.lin.utils.Info.InfoJson;
import com.lin.utils.files.Progress;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = "user", description = "用户管理")
@Controller
@RequestMapping(value = "/a/user")
public class UserApi extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping(value = "/menuPMenu", method = RequestMethod.GET)
	public ResponseEntity<BaseResult> findMenu(@RequestParam(value = "id", required = false) String id) {
		SysMenu entity = new SysMenu();
		entity.setParentId(id);
		List<ZtreeBean> ztreeBeans = new ArrayList<ZtreeBean>();
		List<SysMenu> menus = sysMenuService.findList(entity);
		for (SysMenu sysMenu : menus) {
			ZtreeBean ztreeBean = new ZtreeBean();
			ztreeBean.setId(sysMenu.getId());
			ztreeBean.setpId(sysMenu.getParentId());
			ztreeBean.setName(sysMenu.getName());
			ztreeBean.setNocheck(false);
			ztreeBean.setOpen(true);
			SysMenu pids = new SysMenu();
			pids.setParentId(sysMenu.getId());
			ztreeBean.setisParent(sysMenuService.findList(pids).size() > 1 ? false : true);
			ztreeBeans.add(ztreeBean);
		}
		return buildSuccessResultInfo1(new Gson().toJson(ztreeBeans));
	}

}
