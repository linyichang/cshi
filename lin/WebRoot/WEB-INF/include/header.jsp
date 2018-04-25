<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<style>
.cloud:BEFORE {
	content: "好天气";
	height: 20px;
	position: relative;
	left: 50%;
	top: 20px;
}
</style>
<!DOCTYPE>
<header class="index-header">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">管理平台</a>

			</div>
			<p class="navbar-text navbar-right cloudOn" style="margin-right: 20px; font-weight: bold; font-size: 16px; cursor: pointer;">
				天气 <i class="fa fa-cloud" aria-hidden="true"></i>
			</p>
			<p class="navbar-text navbar-right" style="margin-right: 20px; font-weight: bold; font-size: 16px; text-decoration: n">
				<a id="logout" style="cursor: pointer;"><i class="fa fa-sign-out" aria-hidden="true"></i>退出</a>
			</p>
		</div>
	</nav>
</header>

