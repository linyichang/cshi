<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<title>JSP使用ckfinder实现Ajax文件上传</title>
<style type="text/css">
</style>
</head>
<body style="color: #000;">
	
	<script type="text/javascript">
		$(function() {
			$('.tree li:has(ul)').addClass('parent_li').find(' > span').attr(
					'title', 'Collapse this branch');
			$('.tree li.parent_li > span').on(
					'click',
					function(e) {
						var children = $(this).parent('li.parent_li').find(
								' > ul > li');
						if (children.is(":visible")) {
							children.hide('fast');
							$(this).attr('title', 'Expand this branch').find(
									' > i').addClass('icon-plus-sign')
									.removeClass('icon-minus-sign');
						} else {
							children.show('fast');
							$(this).attr('title', 'Collapse this branch').find(
									' > i').addClass('icon-minus-sign')
									.removeClass('icon-plus-sign');
						}
						e.stopPropagation();
					});
		});
	</script>
	<div class="tree well">
		<ul>
			<li><span><i class="icon-folder-open"></i> 顶级节点1</span> <a href="">Goes somewhere</a>
				<ul>
					<li><span><i class="icon-minus-sign"></i> 一级节点1</span> <a href=""></a>
						<ul>
							<li><span><i class="icon-leaf"></i> 二级节点1_1</span> <a href=""></a></li>
						</ul></li>
					<li><span><i class="icon-minus-sign"></i> 一级节点2</span> <a href=""></a>
						<ul>
							<li><span><i class="icon-leaf"></i>二级节点2_1</span> <a href=""></a></li>
							<li><span><i class="icon-minus-sign"></i> 二级节点2_2</span> <a href=""></a>
								<ul>
									<li><span><i class="icon-minus-sign"></i>三级节点2_1</span> <a href=""></a>
										<ul>
											<li><span><i class="icon-leaf"></i>四级节点2_1</span> <a href=""></a></li>
											<li><span><i class="icon-leaf"></i> 四级节点2_2</span> <a href=""></a></li>
										</ul></li>
									<li><span><i class="icon-leaf"></i>三级节点2_2</span> <a href=""></a></li>
									<li><span><i class="icon-leaf"></i> 三级节点2_3</span> <a href=""></a></li>
								</ul></li>
							<li><span><i class="icon-leaf"></i>二级节点2_3</span> <a href=""></a></li>
						</ul></li>
				</ul></li>
			<li><span><i class="icon-folder-open"></i> 顶级节点2</span> <a href=""></a>
				<ul>
					<li><span><i class="icon-leaf"></i> 一级节点2_1</span> <a href=""></a></li>
				</ul></li>
		</ul>
	</div>
</body>

</html>
