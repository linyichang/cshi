package com.lin.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.lin.sys.entity.SysPerm;
import com.lin.sys.entity.SysRole;
import com.lin.sys.entity.SysRolePerm;
import com.lin.sys.entity.SysRoleUser;
import com.lin.sys.entity.SysUser;
import com.lin.sys.service.SysPermService;
import com.lin.sys.service.SysRolePermService;
import com.lin.sys.service.SysRoleService;
import com.lin.sys.service.SysRoleUserService;
import com.lin.sys.service.SysUserService;

@Component
public class SysRealm extends AuthorizingRealm {
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysRoleUserService sysRoleUserService;
	@Resource
	private SysRolePermService rolePermService;
	@Resource
	private SysRoleService roleService;
	@Resource
	private SysPermService permService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Set<String> roles = new HashSet<String>();
		Set<String> perms = new HashSet<String>();
		
		SysUser sysUser = (SysUser)principals.getPrimaryPrincipal();

		SysRoleUser _ru = new SysRoleUser(null, sysUser.getId());
		
		List<SysRoleUser> roleUserList = sysRoleUserService.findList(_ru);
		
		if(roleUserList != null && roleUserList.size()>0){
			for(SysRoleUser roleUser : roleUserList){
				if(roleUser != null){
					SysRole role = roleService.get(roleUser.getRoleId());
					if(role != null){
//						System.out.println("role : "+role.getName());
						roles.add(role.getName());
					}
					SysRolePerm _rp = new SysRolePerm(role.getId(), null);
					List<SysRolePerm> rolePermList = rolePermService.findList(_rp);
					if(rolePermList != null && rolePermList.size()>0){
						for(SysRolePerm rolePerm : rolePermList){
							SysPerm perm = permService.get(rolePerm.getPermId());
							if(perm != null){
//								System.out.println("perm : "+perm.getPermission());
								perms.add(perm.getPermission());
							}
						}
					}
				}
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roles);
		info.setStringPermissions(perms);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = token.getPrincipal().toString();
		String password = new String((char[])token.getCredentials());
		SysUser _u = new SysUser(username, password, null, null, null);
		List<SysUser> list = sysUserService.findList(_u);
		if(list == null || list.size()<1){
			throw new UnknownAccountException("密码或者账号错误！");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(list.get(0), password, this.getName());
		return info;
	}
	
	

}
