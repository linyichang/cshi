<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="admin-sidebar">
	<div class="nav" style="float: left; position: inherit; width: 100%;">
		<div class="nav-top">
			<div style="border-bottom: 1px solid rgba(255, 255, 255, .1);height: 40px;">
				<a href="#"><i class="fa fa-user-circle fa-fw" aria-hidden="true"></i>&nbsp; 欢迎：${user.username}</a>
			</div>
		</div>
		<ul>
			<li class="nav-item"><a href="javascript:;"><i class="my-icon nav-icon icon_1"></i><span>系统管理</span><i class="my-icon nav-more"></i></a>
				<ul>
					<li><a href="${ctx}/user/list" target="page_context"><span>用户列表</span></a></li>
					<li><a href="${ctx}/role/list" target="page_context"><span>角色列表</span></a></li>
					<li><a href="javascript:;"><span>权限管理</span></a></li>
					<li><a href="javascript:;"><span>菜单管理</span></a></li>
				</ul></li>
			<li class="nav-item"><a href="javascript:;"><i class="my-icon nav-icon icon_2"></i><span>文章管理</span><i class="my-icon nav-more"></i></a>
				<ul>
				   
					<li><a href="javascript:;"><span>站内新闻</span></a></li>
					<li><a href="javascript:;"><span>站内公告</span></a></li>
					<li><a href="javascript:;"><span>登录日志</span></a></li>
				</ul></li>
			<li class="nav-item"><a href="javascript:;"><i class="my-icon nav-icon icon_3"></i><span>订单管理</span><i class="my-icon nav-more"></i></a>
				<ul>
				    <li><a href="${ctx }/store/list" target="page_context"><span>销售统计 <i class="fa fa-bar-chart fa-fw" style="margin-left: 10px;" aria-hidden="true"></i></span></a></li>
					<li><a href="javascript:;"><span>订单列表</span></a></li>
					<li><a href="javascript:;"><span>打个酱油</span></a></li>
					<li><a href="javascript:;"><span>也打酱油</span></a></li>
				</ul></li>
		</ul>
	</div>
</div>
