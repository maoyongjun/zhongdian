<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程物资管理</title>
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
		<li class="active"><a href="${ctx}/dev/material/devMaterialDetail/">工程物资列表</a></li>
		<shiro:hasPermission name="dev:material:devMaterialDetail:edit"><li><a href="${ctx}/dev/material/devMaterialDetail/form">工程物资添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="devMaterialDetail" action="${ctx}/dev/material/devMaterialDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>物资名称：</label>
				<form:input path="materialName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>物资名称</th>
				<th>工程名称</th>
				<th>数量</th>
				<th>备注</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="dev:material:devMaterialDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devMaterialDetail">
			<tr>
				<td><a href="${ctx}/dev/material/devMaterialDetail/form?id=${devMaterialDetail.id}">
					${devMaterialDetail.materialName}
				</a></td>
				<td>
						${devMaterialDetail.project.name}
				</td>
				<td>
					${devMaterialDetail.count}
				</td>
				<td>
					${devMaterialDetail.remarks}
				</td>
				<td>
					<fmt:formatDate value="${devMaterialDetail.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${devMaterialDetail.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="dev:material:devMaterialDetail:edit"><td>
    				<a href="${ctx}/dev/material/devMaterialDetail/form?id=${devMaterialDetail.id}">修改</a>
					<a href="${ctx}/dev/material/devMaterialDetail/delete?id=${devMaterialDetail.id}" onclick="return confirmx('确认要删除该工程物资吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>