<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="in" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户权限列表</title>
<style type="text/css">
</style>
</head>
<body style="color: #000;">
	<div class="panel panel-default" style="margin-bottom: 0px; color: #000; padding: 10px; border: 1px solid rgba(0, 0, 0, 0.1);">
		<sys:message message="${message}" />
		<div class="panel-heading">
			<i class="fa fa-list-alt fa-fw" aria-hidden="true"></i>角色管理
		</div>
		<form:form modelAttribute="sysRole" action="${ctx }/role/list" method="post" class="form-inline">
			<div class="panel-body">
				<div class="form-group">
					<div style="margin: 0px; padding: 0xp; float: left; line-height: 30px;">
						<label for="firstname">昵称：</label>
					</div>
					<div class="col-sm-3" style="float: left;">
						<form:input path="name" class="form-control" placeholder="请输入名称" />
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
					<th>名称</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${page.list}" var="role">
					<tr>
						<td><input type="checkbox" class="allOew" name="allOew" value="${role.id}"/></td>
						<td>${role.name}</td>
						<td>
							<div class="btn-group btn-group-xs" role="group" aria-label="...">
								<a href="${ctx }/role/form?id=${role.id}" style="color: #1FA809;" class="btn btn-default loadImg"> <i class="fa fa-pencil-square-o fa-fw" aria-hidden="true"></i> 编辑
								</a>
								<button type="button" class="btn btn-default" style="color: red;" onclick="javascript:sys.deleteOne('确定要删除吗？', '${ctx}/role/delete?id=${role.id }')">
									<i class="fa fa-trash fa-fw" aria-hidden="true"></i>删除
								</button>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="btn-group" role="group" aria-label="..." style="margin: 10px 0px 10px 0px;">
				<a href="${ctx }/role/form" class="btn btn-default"><i class="fa fa-plus-circle fa-fw" aria-hidden="true"></i> 新增</a>
				<button type="button" class="btn btn-default">
					<i class="fa fa-trash fa-fw" aria-hidden="true"></i> <a href="#">删除</a>
				</button>
				<button type="button" class="btn btn-default">
					<i class="fa fa-pencil-square-o fa-fw" aria-hidden="true"></i> <a href="#">修改</a>
				</button>
				<button type="button" class="btn btn-default" onclick="javascript:location.reload();">
					<i class="fa fa-Refresh fa-fw" aria-hidden="true"></i> 刷新
				</button>
			</div>
			<div class="btn-group" role="group" aria-label="..." style="margin: 10px 0px 10px 0px;"></div>
			<div style="float: right; margin-top: 0px;">
				<sys:page page="${page}" />
			</div>
		</form:form>
		<hr style="margin: 0px;">
		<!-- 		<span>注解：</span> -->
	</div>
	
</body>
<script type="text/javascript">
	$(function() {
		$("#all").click(function() {
			var ch = document.getElementsByName("allOew");
			if (document.getElementById("all").checked == true) {
				for (var i = 0; i < ch.length; i++) {
					ch[i].checked = true;
				}
			} else {
				for (var i = 0; i < ch.length; i++) {
					ch[i].checked = false;
				}
			}
		});
	})
</script>
</html>