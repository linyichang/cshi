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
			<i class="fa fa-info-circle fa-fw" aria-hidden="true"></i>编辑角色
		</div>
		<div class="panel-body">
			<form:form modelAttribute="sysRole" action="${ctx }/role/save" method="post" role="form" class="am-form">
				<form:hidden path="id" />
				<div class="form-group">
					<label for="name">名称</label>
					<form:input path="name" placeholder="请输入名称" class="form-control" style="width:200px;" />
				</div>
				<div class="form-group">
					<label for="name">菜单权限</label>
					<div style="width: 300px; height: 400px; border: 1px solid rgba(0, 0, 0, 0.1); overflow: auto; border-radius: 5px;">
						<div class="zTreeDemoBackground left" style="height: 100%; width: 100%;">
							<ul id="treeDemo" class="ztree" style="height: 100%;"></ul>
						</div>
					</div>
				</div>
		</div>
		<a href="#" class="btn  btn-primary" onclick="javascript:updateMsg('确定要修改吗？')"> <i class="fa fa-check fa-fw" aria-hidden="true"></i>提交
		</a> <a href="${ctx }/role/list" class="btn  btn-default LoadImg"> <i class="fa fa-reply fa-fw" aria-hidden="true"></i>返回
		</a> <a href="javascript:zTreeBeforeCheck();" class="btn  btn-default"> <i class="fa fa-reply fa-fw" aria-hidden="true"></i>获取菜单
		</a>
		</form:form>
	</div>


</body>
<script type="text/javascript">
	function updateMsg(context, url) {
		layer.confirm(context, {
			btn : [ 'YES', 'NO' ]
		//按钮
		}, function() {
			//加载
			sys.load();
			//提交报表
			$(".am-form").submit();

		});
	}

	$(function() {
		var setting = {
			data : {
				simpleData : {
					enable : true
				}
			},
			//异步获取数据
			async : {
				enable : true,
				url : "${pageContext.request.contextPath }/a/user/menuPMenu",
				autoParam : [ "id", "name", "level" ],
				otherParam : {
					"otherParam" : "zTreeAsyncTest"
				},
				dataFilter : filter,
				type : "get"
			},
			check : {
				enable : true,
				chkStyle : "checkbox",
				chkboxType : {
					"Y" : "ps",
					"N" : "s"
				}
			},
			callback : {
			// 				beforeCheck : zTreeBeforeCheck
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
		var zNodes = ${menu};
		$(document).ready(function() {
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});

	})

	function zTreeBeforeCheck() {
		var menuDatas = new Array();
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);
		for ( var i in nodes) {
			menuDatas.push(nodes[i])
		}
		console.log(menuDatas)
	}

</script>
</html>