<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备合同管理</title>
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
		<li class="active"><a href="${ctx}/dev/contract/devContract/">设备合同列表</a></li>
		<shiro:hasPermission name="dev:contract:devContract:edit"><li><a href="${ctx}/dev/contract/devContract/form">设备合同添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="devContract" action="${ctx}/dev/contract/devContract/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>合同名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>采购物品：</label>
				<form:input path="purchaseItems" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li><label>供应商：</label>
				<form:input path="supplier" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>合同名称</th>
				<th>采购物品</th>
				<th>采购人</th>
				<th>供应商</th>
				<th>采购日期</th>
				<th>采购价款</th>
				<th>采购数量</th>
				<shiro:hasPermission name="dev:contract:devContract:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devContract">
			<tr>
				<td><a href="${ctx}/dev/contract/devContract/form?id=${devContract.id}">
					${devContract.name}
				</a></td>
				<td>
					${devContract.purchaseItems}
				</td>
				<td>
					${devContract.purchaser}
				</td>
				<td>
					${devContract.supplier}
				</td>
				<td>
					<fmt:formatDate value="${devContract.purchaseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${devContract.purchasePrice}
				</td>
				<td>
					${devContract.purchaseNumber}
				</td>
				<shiro:hasPermission name="dev:contract:devContract:edit"><td>
    				<a href="${ctx}/dev/contract/devContract/form?id=${devContract.id}">修改</a>
					<a href="${ctx}/dev/contract/devContract/pic?contractId=${devContract.id}">图片</a>
					<a href="${ctx}/dev/contract/devContract/delete?id=${devContract.id}" onclick="return confirmx('确认要删除该设备合同吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>