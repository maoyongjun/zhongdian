<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保养项次管理</title>
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
		<li class="active"><a href="${ctx}/dev/maintaindetail/devMaintainDetail/">保养项次列表</a></li>
		<shiro:hasPermission name="dev:maintaindetail:devMaintainDetail:edit"><li><a href="${ctx}/dev/maintaindetail/devMaintainDetail/form">保养项次添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="devMaintainDetail" action="${ctx}/dev/maintaindetail/devMaintainDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>保养记录id：</label>
				<form:input path="maintainId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>保养的项次：</label>
				<form:input path="itemNumber" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>项次名称：</label>
				<form:input path="itemName" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>保养的项次</th>
				<th>项次名称</th>
				<shiro:hasPermission name="dev:maintaindetail:devMaintainDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devMaintainDetail">
			<tr>
				<td><a href="${ctx}/dev/maintaindetail/devMaintainDetail/form?id=${devMaintainDetail.id}">
					${devMaintainDetail.itemNumber}
				</a>
				</td>
				<td>
					${devMaintainDetail.itemName}
				</td>
				<shiro:hasPermission name="dev:maintaindetail:devMaintainDetail:edit"><td>
    				<a href="${ctx}/dev/maintaindetail/devMaintainDetail/form?id=${devMaintainDetail.id}">修改</a>
					<a href="${ctx}/dev/maintaindetail/devMaintainDetail/delete?id=${devMaintainDetail.id}" onclick="return confirmx('确认要删除该保养项次吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>