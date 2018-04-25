package com.lin.sys.contorller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lin.sys.entity.SysPerm;
import com.lin.sys.service.SysPermService;
import com.lin.sys.utils.Tool;
import com.lin.utils.Info.BaseController;

@Controller
@RequestMapping(value = "/perm")
public class SysPermWebContorller extends BaseController<SysPerm> {

	@Autowired
	private SysPermService sysPermService;

	@ModelAttribute
	private SysPerm get(@RequestParam(value = "id", required = false) String id) {
		SysPerm _Perm = null;
		if (Tool.isNotBlank(id)) {
			_Perm = sysPermService.get(id);
		} else {
			_Perm = new SysPerm();
		}
		return _Perm;
	}
	
	@RequiresPermissions(value="sys:perm:perm:view")
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(SysPerm perm){
		
		return returnPath("sys/premList");
	}
}
