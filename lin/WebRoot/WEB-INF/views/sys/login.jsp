<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/sysTag" prefix="sys" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/static/jquery/jquery-1.8.3.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/sha/js/sha.js" charset="utf-8"></script>
<!doctype html>
<html>
<head>
<title>管理后台登陆</title>
<style>
.login-index {
	width: 500px;
	height: 350px;
	background-color: rgba(0, 0, 0, 0.2);
	margin: 0 auto;
	margin-top: 5%;
	border-radius: 10px;
	border-bottom: 10px solid rgba(0, 0, 0, 0.3);
	border-left: 15px solid rgba(0, 0, 0, 0.3);
	border-right: 15px solid rgba(0, 0, 0, 0.3);
	border-top: 10px solid rgba(0, 0, 0, 0.3);
	color: #000;
	opacity: 0;
	position: relative;
	top: -10%;
	transition: top 1s, opacity 0.8s ,transform 0.5s;
}

/* .login-index:HOVER { */
/* 	transform:scale(1.02); */
/* } */

.form-tools {
	margin: 0 auto;
	/* 	color: #fff; */
}
</style>
</head>
<body>
    <sys:message message="${message}"/>${message}
	<div class="login-index">
		<h1 style="text-align: center; color: #fff; color: #000;">管理平台</h1>
		<hr>
		<form action="${pageContext.request.contextPath }/user/lg/login" method="post" class="am-form am-form-horizontal"
			style="width: 400px; color: #fff; margin: 0 auto; margin-top: 20px; text-align: center;">
			<div class="input-group" style="margin-left: 19%; margin-top: 30px;">
				<div style="float: left;">
					<span style="line-height: 32px; color: #000;">账号：</span>
				</div>
				<div style="float: left;">
					<input type="text" name="username" id="username" class="form-control" placeholder="请输入账号" aria-describedby="basic-addon1">
				</div>
			</div>
			<div class="input-group" style="margin-left: 19%; margin-top: 20px;">
				<div style="float: left;">
					<span style="line-height: 32px; color: #000;">密码：</span>
				</div>
				<div style="float: left;">
					<input type="password" name="password" id="password" class="form-control" placeholder="请输入密码" aria-describedby="basic-addon1">
				</div>
			</div>
			<p style="width: 100%;">
				<button id="inputForm" type="submit" class="btn btn-primary" style="margin-top: 20px; margin-left: 32px;">确定登录</button>

				<button type="button" class="btn btn-default" style="margin-top: 20px; margin-left: 25px;">忘记密码</button>
			</p>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(
			function() {
				var loginEorror = '${loginEorror}';
				// 让登陆页面始终在顶级窗口打开
				if (window.name == "main") {
					window.top.location.href = "/lin/user/login"
				}
				// 错误消息不为空时显示div
				var msg = $("#error-message").text();
				if (msg != null && msg != '' && msg != 'null') {
					$(".div-error").css("display", "block");
				}
				// 表单提交
				$("#inputForm").click(

						function() {
							var username = $("#username").val();
							var password = $("#password").val();
							// 去除空格
							var reg = new RegExp(' ', "gm");
							username = username.replace(reg, '');
							password = password.replace(reg, '');
							// 非空校验
							if (typeof (username) != 'undefined'
									&& username != null && username != ''
									&& typeof (password) != 'undefined'
									&& password != null && password != '') {
								// 密码sha1加密
								var shaObj = new jsSHA("SHA-1", "TEXT");
								shaObj.update(password);
								password = shaObj.getHash("HEX");
								//alert($("#username").val()+"  "+$("#password").val())
								$("#username").val(username);
								$("#password").val(password);
								return true;
							}
							return false;
						});
				$('.login-index').css({
					'opacity' : 1,
					'top' : '-5%'
				})
				//选中
				$("#username").focus();
			});
</script>
</html>
