<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body style="color: #000;">
	<div class="panel panel-default del-style-left" style="margin-bottom: 0px; color: #000; padding: 10px; border: 1px solid rgba(0, 0, 0, 0.1);">
		<sys:message message="${message}" />
		<div class="panel-heading">
			<i class="fa fa-list-alt fa-fw" aria-hidden="true"></i>用户管理
		</div>
		<form:form modelAttribute="sysUser" action="${ctx }/user/list" method="post" class="form-inline">
			<div class="panel-body">
				<div class="form-group">
					<div style="margin: 0px; padding: 0xp; float: left; line-height: 30px;">
						<label for="firstname">账号：</label>
					</div>
					<div class="col-sm-3" style="float: left;">
						<form:input path="username" class="form-control" placeholder="请输入密码" />
					</div>
				</div>
				<div class="form-group">
					<div style="margin: 0px; padding: 0xp; float: left; line-height: 30px;">
						<label for="firstname">昵称：</label>
					</div>
					<div class="col-sm-3" style="float: left;">
						<form:input path="nickname" class="form-control" placeholder="请输入昵称" />
					</div>
				</div>
				<div class="form-group">
					<div style="margin: 0px; padding: 0xp; float: left; line-height: 30px;">
						<label for="firstname">性别：</label>
					</div>
					<div class="col-sm-3" style="float: left;">
						<form:select path="gender" class="form-control">
							<form:option value="">--</form:option>
							<form:option value="1">男</form:option>
							<form:option value="2">女</form:option>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<div style="margin: 0px; padding: 0xp; float: left; line-height: 30px;">
						<button type="submit" class="btn btn-primary">
							<i class="fa fa-search fa-fw" aria-hidden="true"></i>搜索
						</button>
					</div>
				</div>
			</div>
			<table class="table table-bordered" style="color: #000;">
				<tr>
					<th style="width: 20px;"><input type="checkbox" id="all" /></th>
					<th>用户名</th>
					<th>用户昵称</th>
					<th>性别</th>
					<th>是否被锁</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${page.list}" var="user">
					<tr>
						<td><input type="checkbox" class="allOew" name="allOew" value="${user.id}" /></td>
						<td>${user.username}</td>
						<td>${user.nickname}</td>
						<td><c:if test="${user.gender == 1}">男</c:if> <c:if test="${user.gender == 2}">女</c:if></td>
						<td><c:if test="${user.locked == 1}">是</c:if> <c:if test="${user.locked == 0}">否</c:if></td>
						<td>
							<div class="btn-group btn-group-xs" role="group" aria-label="...">

								<a href="${ctx }/user/form?id=${user.id}" class="btn btn-default" style="color: #1FA809;"><i class="fa fa-pencil-square-o fa-fw" aria-hidden="true"></i>编辑</a>

								<button type="button" class="btn btn-default" style="color: red;" onclick="javascript:updateMsg('确定要删除吗？', '${ctx}/user/delete?id=${user.id }')">
									<i class="fa fa-trash fa-fw" aria-hidden="true"></i>删除
								</button>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="btn-group" role="group" aria-label="..." style="margin: 10px 0px 10px 0px;">
				<button type="button" class="btn btn-default">
					<i class="fa fa-plus-circle fa-fw" aria-hidden="true"></i>新增
				</button>
				<button type="button" class="btn btn-default">
					<i class="fa fa-trash fa-fw" aria-hidden="true"></i>删除
				</button>
				<button type="button" class="btn btn-default">
					<i class="fa fa-pencil-square-o fa-fw" aria-hidden="true"></i>修改
				</button>
			</div>
			<div class="btn-group" role="group" aria-label="..." style="margin: 10px 0px 10px 0px;"></div>
			<div style="float: right; margin-top: 0px;">
				<sys:page page="${page}" />
			</div>
		</form:form>
		<hr style="margin: 0px;">
		<span>注解：</span>
	</div>
</body>
</html>
<script type="text/javascript">
	function updateMsg(context, url) {
		layer.confirm(context, {
			btn : [ 'YES', 'NO' ]
		//按钮
		}, function() {
			window.location.href = url;
		});

	}
</script>