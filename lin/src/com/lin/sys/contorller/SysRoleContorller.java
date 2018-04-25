package com.lin.sys.contorller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.lin.entity.ZtreeBean;
import com.lin.sys.entity.SysMenu;
import com.lin.sys.entity.SysRole;
import com.lin.sys.entity.SysRolePerm;
import com.lin.sys.service.SysMenuService;
import com.lin.sys.service.SysRolePermService;
import com.lin.sys.service.SysRoleService;
import com.lin.sys.utils.Tool;
import com.lin.utils.SysPage;
import com.lin.utils.Info.BaseController;

@Controller
@RequestMapping(value = "/role")
public class SysRoleContorller extends BaseController<SysRole> {

	@Autowired
	private SysRolePermService sysRolePermService;

	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	public SysMenuService sysMenuService;

	@ModelAttribute
	public SysRole get(@RequestParam(required = false) String id) {
		SysRole role = null;
		if (Tool.isNotBlank(id)) {
			role = sysRoleService.get(id);
		} else {
			role = new SysRole();
		}
		return role;
	}

	@RequiresPermissions(value = "sys:devp:view")
	@RequestMapping(value = "/list")
	public String list(SysRole sysRole, Model model, HttpServletRequest request, HttpServletResponse response) {
		SysPage<SysRole> page = sysRoleService.findPage(sysRole, request);
		model.addAttribute("page", page);
		return returnPath("sys/roleList");
	}

	@RequiresPermissions(value = "sys:devp:view")
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(SysRole sysRole, Model model) {
		model.addAttribute("form", sysRole);
		SysMenu menu = new SysMenu();
//		menu.setLevel("1");
		List<SysMenu> menus = sysMenuService.findList(menu);
		List<ZtreeBean> ztreeBeans = new ArrayList<ZtreeBean>();
		for (SysMenu sysMenu : menus) {
			ZtreeBean ztreeBean = new ZtreeBean();
			ztreeBean.setId(sysMenu.getId());
			ztreeBean.setpId(sysMenu.getParentId());
			ztreeBean.setName(sysMenu.getName());
			ztreeBean.setNocheck(false);
			ztreeBean.setOpen(true);
			SysMenu pids = new SysMenu();
			pids.setParentId(sysMenu.getId());
			ztreeBean.setisParent(sysMenuService.findList(pids).size() > 0?true:false);
			ztreeBeans.add(ztreeBean);
		}
		
		model.addAttribute("menu", new Gson().toJson(ztreeBeans));
		return returnPath("sys/roleForm");
	}

	@RequiresPermissions(value = "sys:user:user:edit")
	@RequestMapping(value = "/delete")
	public String delete(SysRole role, RedirectAttributes redirectAttributes) {
		if (role != null) {
			sysRoleService.delete(role);
			addMessage("删除成功！", SUCCESS, redirectAttributes);
		}
		return "redirect:list";
	}

	@RequiresPermissions(value = "sys:user:user:edit")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(SysRole _role, RedirectAttributes redirectAttributes) {
		if (_role != null) {
			sysRoleService.save(_role);
			addMessage("保存成功！", SUCCESS, redirectAttributes);
		}
		return "redirect:list";
	}
}
