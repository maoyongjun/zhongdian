<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入库单管理</title>
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
		<li class="active"><a href="${ctx}/dev/warehousereceipt/devWarehouseReceipt/">入库单列表</a></li>
		<shiro:hasPermission name="dev:warehousereceipt:devWarehouseReceipt:edit"><li><a href="${ctx}/dev/warehousereceipt/devWarehouseReceipt/form">入库单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="devWarehouseReceipt" action="${ctx}/dev/warehousereceipt/devWarehouseReceipt/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>入库单名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>入库单名称</th>
				<th>入库单时间</th>
				<th>采购人</th>
				<th>库管</th>
				<th>审批人</th>
				<shiro:hasPermission name="dev:warehousereceipt:devWarehouseReceipt:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devWarehouseReceipt">
			<tr>
				<td><a href="${ctx}/dev/warehousereceipt/devWarehouseReceipt/formDetail?id=${devWarehouseReceipt.id}">
					${devWarehouseReceipt.name}
				</a></td>
				<td>
					<fmt:formatDate value="${devWarehouseReceipt.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${devWarehouseReceipt.purchaser}
				</td>
				<td>
					${devWarehouseReceipt.warehouseManagement}
				</td>
				<td>
					${devWarehouseReceipt.approver}
				</td>
				<shiro:hasPermission name="dev:warehousereceipt:devWarehouseReceipt:edit"><td>
    				<a href="${ctx}/dev/warehousereceipt/devWarehouseReceipt/formDetail?id=${devWarehouseReceipt.id}">修改</a>
					<a href="${ctx}/dev/warehousereceipt/devWarehouseReceipt/delete?id=${devWarehouseReceipt.id}" onclick="return confirmx('确认要删除该入库单吗？', this.href)">删除</a>
					<a href="#">导出</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>