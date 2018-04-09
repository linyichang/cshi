<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ztree学习</title>
<link rel="stylesheet" href="static/zTree/css/demo.css" type="text/css">
<link rel="stylesheet" href="static/zTree/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="static/zTree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="static/zTree/js/jquery.ztree.core.js"></script>

</head>
<body>
<div class="zTreeDemoBackground left" style="height: 500px;">
  <ul id="treeDemo" class="ztree" style="height: 100%;"></ul>
</div> 
</body>
<script type="text/javascript">

	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		//异步获取数据
		async : {
			enable : true,
			url : "${pageContext.request.contextPath }/test",
			autoParam : [ "id", "name", "level" ],
			otherParam : {"otherParam" : "zTreeAsyncTest"},
			dataFilter : filter,
			type:"post"
		}
	};

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for (var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	var zNodes = [ 
	{id : 1,pId : 0,name : "功能菜单",isParent : true}
	]		
	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
</script>
</html>