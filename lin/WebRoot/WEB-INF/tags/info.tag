<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@ attribute name="formId" type="java.lang.String" required="false" description="表单ID"%>
<%@ attribute name="page" type="com.lin.utils.SysPage" required="false" description="分页数据"%>
<ul class="pagination">
	<li><a href="${page.url}?id=11">下一页</a></li>
	<li><a href="#">${page.pageTotal}</a></li>
	<li><a href="${page.url}?id=11">上一页</a></li>
	<li class='disabled'><a href='javascript:;'>共&nbsp;${page.pageTotal}&nbsp;页，&nbsp;${page.count}&nbsp;条</a></li>
</ul>
<script>
  var formId = document.getElementById('${formId}');
  var input = $(formId);
  
</script>