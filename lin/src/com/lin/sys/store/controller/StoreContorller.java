package com.lin.sys.store.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lin.sys.entity.SysPerm;
import com.lin.sys.entity.SysUser;
import com.lin.sys.store.entity.Store;
import com.lin.sys.store.service.StoreService;
import com.lin.sys.utils.Tool;
import com.lin.utils.SysPage;
import com.lin.utils.Info.BaseController;
import com.test.Test;

@Controller
@RequestMapping(value="/store")
public class StoreContorller extends BaseController<Store>{

	@Autowired
	private StoreService storeService;
	
	@ModelAttribute
	public Store get(@RequestParam(value = "id", required = false) String id) {
		Store store = null;
		if (Tool.isNotBlank(id)) {
			store = storeService.get(id);
		} else {
			store = new Store();
		}
		new StoreService();
		return store;
	}

	@RequestMapping(value="/list",method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Store store,HttpServletRequest request,Model model){
		SysPage<Store> page = storeService.findPage(store, request);
		Map<String, String> queryTQJson = Test.queryTQJson();
		model.addAttribute("low", queryTQJson.get("low"));
		model.addAttribute("time", queryTQJson.get("time"));
		model.addAttribute("high", queryTQJson.get("high"));
		model.addAttribute("page", page);
		return returnPath("/store/storeList");
	}
}
