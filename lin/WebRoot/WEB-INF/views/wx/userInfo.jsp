<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/jquery/jquery-2.1.4.min.js"></script>
<style type="text/css">
.fiedlset {
	border-radius: 5px;
}

.img {
	cursor: pointer;
}

</style>
</head>
<body>
	<fieldset class="fiedlset">
		<img alt="" src="${userInfo.headimgurl }" style="border-radius: 50%;" onclick="disimg(this)" class="img">
		<legend>用户信息</legend>
		<br> <label>用户名：</label>${userInfo.nickname}<br>
		<br> <label>年龄：</label>${userInfo.sex}<br>
		<br> <label>现在所住地：</label>${userInfo.country} ${userInfo.province}省 ${userInfo.city}市
	</fieldset>
    

</body>
<script type="text/javascript">
	function disimg(o) {
		alert($('.img').attr('src'))
	}
	 function queryTQ(){
	       $.get("http://www.sojson.com/open/api/weather/json.shtml?city=湛江",function(data){
	          alert(data);
	       })
	   }
	    queryTQ();
	
</script>


</html>