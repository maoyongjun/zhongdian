<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备调拨管理</title>
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
		<li class="active"><a href="${ctx}/dev/allocation/devAllocation/">设备调拨列表</a></li>
		<shiro:hasPermission name="dev:allocation:devAllocation:edit"><li><a href="${ctx}/dev/allocation/devAllocation/form?devtype=${devAllocation.devtype}">设备调拨添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="devAllocation" action="${ctx}/dev/allocation/devAllocation/?devtype=${devAllocation.devtype}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>调拨单名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('allocation_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>设备类型：</label>
				<form:select path="devtype" class="input-medium" disabled="true">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dev_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>调拨单名称</th>
				<th>出库的项目</th>
				<th>入库的项目</th>
				<th>调拨时间</th>
				<th>状态</th>
				<th>设备类型</th>
				<shiro:hasPermission name="dev:allocation:devAllocation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devAllocation">
			<tr>
				<td><a href="${ctx}/dev/allocation/devAllocation/formDetail?id=${devAllocation.id}">
					${devAllocation.name}
				</a></td>
				<td>
					${devAllocation.projectCheckout}
				</td>
				<td>
					${devAllocation.projectCheckin}
				</td>
				<td>
					<fmt:formatDate value="${devAllocation.allocationDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(devAllocation.status, 'allocation_status', '')}
				</td>
				<td>
					${fns:getDictLabel(devAllocation.devtype, 'dev_type', '')}
				</td>
				<shiro:hasPermission name="dev:allocation:devAllocation:edit"><td>
    				<a href="${ctx}/dev/allocation/devAllocation/formDetail?id=${devAllocation.id}">修改</a>
					<a href="${ctx}/dev/allocation/devAllocation/delete?id=${devAllocation.id}" onclick="return confirmx('确认要删除该设备调拨吗？', this.href)">删除</a>
                    <c:if test="${devAllocation.status==1}">
						<a href="${ctx}/dev/allocation/devAllocation/sureAllocate?id=${devAllocation.id}">确认调拨</a>
					</c:if>				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>