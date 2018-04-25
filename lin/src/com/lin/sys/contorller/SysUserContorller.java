package com.lin.sys.contorller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lin.sys.entity.SysUser;
import com.lin.sys.service.SysUserService;
import com.lin.sys.utils.Tool;
import com.lin.utils.SysPage;
import com.lin.utils.Info.BaseController;

@Controller
@RequestMapping("/user")
public class SysUserContorller extends BaseController<SysUser> {

	@Autowired
	private SysUserService userService;

	@ModelAttribute
	public SysUser get(@RequestParam(value = "id", required = false) String id) {
		SysUser sysUser = null;
		if (Tool.isNotBlank(id)) {
			sysUser = userService.get(id);
		} else {
			sysUser = new SysUser();
		}
		return sysUser;
	}

	@RequiresPermissions(value = "sys:devp:view")
	@RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
	public String list(SysUser sysUser, Model model, HttpServletRequest request) {
		SysPage<SysUser> page = userService.findPage(sysUser, request);
		model.addAttribute("page", page);
		return returnPath("sys/userList");
	}

	@RequiresPermissions(value = "sys:user:user:edit")
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(SysUser sysUser, Model model) {
		model.addAttribute("form", sysUser);
		
		
		return returnPath("sys/userForm");
	}

	@RequiresPermissions(value = "sys:user:user:edit")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(SysUser user, RedirectAttributes redirectAttributes) {
		if (user != null) {
			userService.save(user);
			addMessage("保存成功！", SUCCESS, redirectAttributes);
		} else {
			addMessage("保存失败！", ERROR, redirectAttributes);
		}
		return "redirect:list";
	}

	@RequiresPermissions(value = "sys:user:user:edit")
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(SysUser sysUser, RedirectAttributes redirectAttributes) {

		if (sysUser != null) {
			userService.delete(sysUser);
			addMessage("删除成功！", SUCCESS, redirectAttributes);
		} else {
			addMessage("删除失败！", ERROR, redirectAttributes);
		}

		return "redirect:list";
	}

	@RequiresPermissions(value = "sys:user:user:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.GET)
	public String deleteDate(@RequestParam("ids") String ids) {
		if (ids != null && !" ".equals(ids)) {
			String[] idss = ids.split(",");
			for (String id : idss) {
				SysUser user = new SysUser();
				user.setId(id);
				userService.delete(user);
			}
		}
		return returnPath("UserList");
	}

}
