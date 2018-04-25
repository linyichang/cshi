<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<style type="text/css">
</style>
</head>
<body style="color: #000;">
	<div class="panel panel-default del-style-left" style="margin-bottom: 0px; color: #000; padding: 10px; border: 1px solid rgba(0, 0, 0, 0.1);">
		<div class="panel-heading">
			<i class="fa fa-list-alt fa-fw" aria-hidden="true"></i>用户管理
		</div>
		<div class="panel-body">
			<form:form modelAttribute="sysUser" action="${ctx }/user/save" method="post" role="form" class="am-form">
				<form:hidden path="id" />
				<div class="form-group">
					<label for=name>名称</label>
					<form:input path="username" placeholder="请输入名称" class="form-control" style="width:200px;" />
				</div>
				<div class="form-group">
					<label for=name>昵称</label>
					<form:input path="nickname" placeholder="请输入名称" class="form-control" style="width:200px;" />
				</div>
				<div class="form-group">
					<label for=gender>性别</label>
					<form:select path="gender" class="form-control" style="width:100px;" >
						<form:option value="">--</form:option>
						<form:option value="1">男</form:option>
						<form:option value="2">女</form:option>
					</form:select>
				</div>
				<div class="form-group">
					<label for=locked>性别</label>
					<form:select path="locked" class="form-control" style="width:100px;" >
						<form:option value="">--</form:option>
						<form:option value="1">是</form:option>
						<form:option value="0">否</form:option>
					</form:select>
				</div>
			</form:form>
		</div>
		<a href="#" class="btn  btn-primary" onclick="javascript:updateMsg('确定要保存吗？')"> <i class="fa fa-check fa-fw" aria-hidden="true"></i>提交
		</a> <a href="${ctx }/user/list" class="btn  btn-default"> <i class="fa fa-reply fa-fw" aria-hidden="true"></i>返回
		</a>
	</div>
</body>
</html>
<script type="text/javascript">
	function updateMsg(context, url) {
		layer.confirm(context, {
			btn : [ 'YES', 'NO' ]
		//按钮
		}, function() {
			$(".am-form").submit();

		});
	}
</script>