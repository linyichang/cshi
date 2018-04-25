package com.lin.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lin.service.BaseService;
import com.lin.sys.dao.SysMenuDao;
import com.lin.sys.entity.SysMenu;

@Service
public class SysMenuService extends BaseService<SysMenuDao, SysMenu>{

	/**
	 * 根据菜单父级关系查询所有菜单，嵌套childList
	 * @return List<SysMenu>
	 */
	public List<SysMenu> findListOrderByLevel(){
		SysMenu _m1 = new SysMenu();
		_m1.setOrderBy("a.rank ASC");
		_m1.setLevel("1");
		_m1.setHide("0");
		List<SysMenu> list = findList(_m1);
		if(list != null && list.size()>0){
			for(SysMenu menuOne : list){
				SysMenu _m2 = new SysMenu();
				_m2.setOrderBy("a.rank ASC");
				_m2.setLevel("2");
				_m2.setHide("0");
				_m2.setParentId(menuOne.getId());
				List<SysMenu> menuTwoList = findList(_m2);
				if(menuTwoList != null && menuTwoList.size()>0){
					for(SysMenu menuTwo :menuTwoList){
						SysMenu _m3 = new SysMenu();
						_m3.setOrderBy("a.rank ASC");
						_m3.setLevel("3");
						_m3.setHide("0");
						_m3.setParentId(menuTwo.getId());
						List<SysMenu> menuThreeList = findList(_m3);
						if(menuThreeList != null && menuThreeList.size()>0){
							menuTwo.setChildList(menuThreeList);
						}
					}
					menuOne.setChildList(menuTwoList);
				}
			}
		}
		return list;
	}
	
	/**
	 * 根据菜单父级关系查询所有菜单，不嵌套childList
	 * @return List<SysMenu>
	 */
	public List<SysMenu> findListOrderByLevelMerge(){
		List<SysMenu> list = new ArrayList<SysMenu>();
		List<SysMenu> listLevel = findListOrderByLevel();
		// 一级菜单
		for(SysMenu menuOne : listLevel){
			list.add(menuOne);
			if(menuOne.getChildList() != null && menuOne.getChildList().size()>0){
				for(SysMenu menuTwo : menuOne.getChildList()){
					list.add(menuTwo);
					if(menuTwo.getChildList() != null && menuTwo.getChildList().size()>0){
						for(SysMenu menuThree : menuTwo.getChildList()){
							list.add(menuThree);
						}
					}
				}
			}
		}
		return list;
	}
	
}
