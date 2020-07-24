<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>类目维护管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pj/category/pjValueCategory/">类目列表</a></li>
		<shiro:hasPermission name="pj:category:pjValueCategory:edit"><li><a href="${ctx}/pj/category/pjValueCategory/merge">类目合并</a></li></shiro:hasPermission>
		<shiro:hasPermission name="pj:category:pjValueCategory:edit"><li><a href="${ctx}/pj/category/pjValueCategory/form">类目添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pjValueCategory" action="${ctx}/pj/category/pjValueCategory/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>状态</th>
				<th>创建日期</th>
				<shiro:hasPermission name="pj:category:pjValueCategory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pjValueCategory">
			<tr>
				<td><a href="${ctx}/pj/category/pjValueCategory/form?id=${pjValueCategory.id}">
					${pjValueCategory.name}
				</a></td>
				<td>
					${fns:getDictLabel(pjValueCategory.statu, 'pj_category_status', '')}
				</td>

				<td>
					<fmt:formatDate value="${pjValueCategory.createDate}" pattern="yyyy-MM-dd"/>
				</td>

				<shiro:hasPermission name="pj:category:pjValueCategory:edit"><td>
    				<a href="${ctx}/pj/category/pjValueCategory/form?id=${pjValueCategory.id}">修改</a>
					<a href="${ctx}/pj/category/pjValueCategory/delete?id=${pjValueCategory.id}" onclick="return confirmx('确认要删除该类目吗？', this.href)">删除</a>
					<a href="${ctx}/pj/category/pjValueCategory/openStatus?id=${pjValueCategory.id}" onclick="return confirmx('确认要开启该类目吗？', this.href)">开启</a>
					<a href="${ctx}/pj/category/pjValueCategory/closeStatus?id=${pjValueCategory.id}" onclick="return confirmx('确认要关闭该类目吗？', this.href)">关闭</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>