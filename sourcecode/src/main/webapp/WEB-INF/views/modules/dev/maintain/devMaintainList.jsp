<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备保养管理</title>
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

		<li class="active"><a href="${ctx}/dev/maintain/devMaintain/">设备保养列表</a></li>
		<shiro:hasPermission name="dev:maintain:devMaintain:edit"><li><a href="${ctx}/dev/maintain/devMaintain/form?devId=${devId}&devName=${devName}">设备保养添加</a></li></shiro:hasPermission>
	</ul>

	<form:form id="searchForm" modelAttribute="devMaintain" action="${ctx}/dev/maintain/devMaintain/" method="post" class="breadcrumb form-search">

		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备id：</label>
				<form:input path="devId" htmlEscape="false" maxlength="64" class="input-medium"  value="${devId}"  disabled="true"/>
			</li>

			<li><label>保养人：</label>
				<form:input path="maintainBy" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>设备名称：</label>
				<form:input path="devName" htmlEscape="false" maxlength="255" class="input-medium"  value="${devName}"  disabled="true"/>
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
				<th>保养时间</th>
				<th>保养人</th>
				<th>设备名称</th>
				<shiro:hasPermission name="dev:maintain:devMaintain:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devMaintain">
			<tr>
				<td><a href="${ctx}/dev/maintain/devMaintain/form?id=${devMaintain.id}">
					${devMaintain.devId}
				</a></td>
				<td>
					<fmt:formatDate value="${devMaintain.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${devMaintain.maintainBy}
				</td>
				<td>
					${devMaintain.devName}
				</td>
				<shiro:hasPermission name="dev:maintain:devMaintain:edit"><td>
    				<a href="${ctx}/dev/maintain/devMaintain/form?id=${devMaintain.id}">修改</a>
					<a href="${ctx}/dev/maintain/devMaintain/delete?id=${devMaintain.id}" onclick="return confirmx('确认要删除该设备保养吗？', this.href)">删除</a>
					<a href="${ctx}/dev/maintaindetail/devMaintainDetail/list?maintainId=${devMaintain.id}">保养详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>