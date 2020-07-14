<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>核销单明细管理</title>
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
		<li class="active"><a href="${ctx}/dev/writeoffdetail/devWriteOffDetail/">核销单明细列表</a></li>
		<shiro:hasPermission name="dev:writeoffdetail:devWriteOffDetail:edit"><li><a href="${ctx}/dev/writeoffdetail/devWriteOffDetail/form">核销单明细添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="devWriteOffDetail" action="${ctx}/dev/writeoffdetail/devWriteOffDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备id：</label>
				<form:input path="devid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>设备名称：</label>
				<form:input path="devname" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li><label>核销单id：</label>
				<form:input path="writeoffId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>项目id：</label>
				<form:input path="projectid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>项目名称：</label>
				<form:input path="projectname" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>设备id</th>
				<th>设备名称</th>
				<th>核销单id</th>
				<th>项目id</th>
				<th>项目名称</th>
				<shiro:hasPermission name="dev:writeoffdetail:devWriteOffDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devWriteOffDetail">
			<tr>
				<td><a href="${ctx}/dev/writeoffdetail/devWriteOffDetail/form?id=${devWriteOffDetail.id}">
					${devWriteOffDetail.devid}
				</a></td>
				<td>
					${devWriteOffDetail.devname}
				</td>
				<td>
					${devWriteOffDetail.writeoffId}
				</td>
				<td>
					${devWriteOffDetail.projectid}
				</td>
				<td>
					${devWriteOffDetail.projectname}
				</td>
				<shiro:hasPermission name="dev:writeoffdetail:devWriteOffDetail:edit"><td>
    				<a href="${ctx}/dev/writeoffdetail/devWriteOffDetail/form?id=${devWriteOffDetail.id}">修改</a>
					<a href="${ctx}/dev/writeoffdetail/devWriteOffDetail/delete?id=${devWriteOffDetail.id}" onclick="return confirmx('确认要删除该核销单明细吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>