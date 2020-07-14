<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调拨细项管理</title>
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
		<li class="active"><a href="${ctx}/dev/allocationdetail/devAllocationDetail/">调拨细项列表</a></li>
		<shiro:hasPermission name="dev:allocationdetail:devAllocationDetail:edit"><li><a href="${ctx}/dev/allocationdetail/devAllocationDetail/form">调拨细项添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="devAllocationDetail" action="${ctx}/dev/allocationdetail/devAllocationDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>调拨单id：</label>
				<form:input path="allocationId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>序号：</label>
				<form:input path="itemNumber" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>设备id：</label>
				<form:input path="devId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>设备名称：</label>
				<form:input path="devName" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>调拨单id</th>
				<th>序号</th>
				<th>设备id</th>
				<th>设备名称</th>
				<shiro:hasPermission name="dev:allocationdetail:devAllocationDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devAllocationDetail">
			<tr>
				<td><a href="${ctx}/dev/allocationdetail/devAllocationDetail/form?id=${devAllocationDetail.id}">
					${devAllocationDetail.allocationId}
				</a></td>
				<td>
					${devAllocationDetail.itemNumber}
				</td>
				<td>
					${devAllocationDetail.devId}
				</td>
				<td>
					${devAllocationDetail.devName}
				</td>
				<shiro:hasPermission name="dev:allocationdetail:devAllocationDetail:edit"><td>
    				<a href="${ctx}/dev/allocationdetail/devAllocationDetail/form?id=${devAllocationDetail.id}">修改</a>
					<a href="${ctx}/dev/allocationdetail/devAllocationDetail/delete?id=${devAllocationDetail.id}" onclick="return confirmx('确认要删除该调拨细项吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>