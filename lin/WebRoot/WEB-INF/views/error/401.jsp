<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未登录</title>
</head>
<body>
<h2 style="color: #000;">对不起，你没有登录，<a href="#" style="font-family: '楷体';text-decoration: none;" onclick="javascript:tologin()">《点我重新登录》</a>！</h2>

</body>
<script type="text/javascript">
function tologin(){
	top.document.location.href = "${pageContext.request.contextPath}/user/lg/login";
}
</script>
</html>