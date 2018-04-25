<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理平台</title>
<style type="text/css">
html {
	width: 100%;
	height: 100%;
	margin: 0px;
}

body {
	width: 100%;
	height: 100%;
	margin: 0px;
	color: #000;
}

.index-admin {
	width: 100%;
	height: 100%;
	margin: 0px;
}

.index-header {
	width: 100%;
	float: left;
	height: 50px;
}

.admin-sidebar {
	width: 250px;
	height: calc(100% - 50px);
	float: left;
}

.admin-dema {
	width: calc(100% - 250px);
	height: 40px;
	float: left;
	background-color: #263238;
	float: left;
}

.admin-context {
	width: calc(100% - 250px);
	height: calc(100% - 100px);
	float: left;
}
</style>
</head>
<body>
	<div class="index-admin">
	<jsp:include page="/WEB-INF/include/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/include/sidebar.jsp"></jsp:include>
		<div class="admin-dema"></div>
		<div class="admin-context">
			<iframe src="${ctx }/Welcome.jsp" style="width: 100%; height: 100%;border: 0px;margin: 0px;" name="page_context" ></iframe>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$("#logout").click(function() {
			layer.confirm('您确定要退出吗？', {
				btn : [ '是', '否' ]
			//按钮
			}, function() {
				window.location.href = "${ctx }/logout";
			});
		});
	})
	
	$(".cloudOn").mouseover(function() {
		$(this).addClass("cloud");
	});
	$(".cloudOn").mouseout(function(){
		$(this).attr("class","navbar-text navbar-right cloudOn");
	});
</script>
</html>