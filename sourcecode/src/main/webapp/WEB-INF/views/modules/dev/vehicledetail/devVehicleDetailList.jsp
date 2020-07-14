<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆相关记录管理</title>
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
		<li class="active"><a href="${ctx}/dev/vehicledetail/devVehicleDetail/">车辆相关记录列表</a></li>
		<shiro:hasPermission name="dev:vehicledetail:devVehicleDetail:edit"><li><a href="${ctx}/dev/vehicledetail/devVehicleDetail/form">车辆相关记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="devVehicleDetail" action="${ctx}/dev/vehicledetail/devVehicleDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('vehicle_maintain_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>车辆id：</label>
				<form:input path="vehicleId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>维护人：</label>
				<form:input path="maintainer" htmlEscape="false" maxlength="200" class="input-medium"/>
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
				<th>类型</th>
				<th>日期</th>
				<th>车辆id</th>
				<th>部件</th>
				<th>里程数</th>
				<th>费用</th>
				<th>维护人</th>
				<shiro:hasPermission name="dev:vehicledetail:devVehicleDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devVehicleDetail">
			<tr>
				<td><a href="${ctx}/dev/vehicledetail/devVehicleDetail/form?id=${devVehicleDetail.id}">
					${devVehicleDetail.name}
				</a></td>
				<td>
					${fns:getDictLabel(devVehicleDetail.type, 'vehicle_maintain_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${devVehicleDetail.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${devVehicleDetail.vehicleId}
				</td>
				<td>
					${devVehicleDetail.parts}
				</td>
				<td>
					${devVehicleDetail.mileage}
				</td>
				<td>
					${devVehicleDetail.cost}
				</td>
				<td>
					${devVehicleDetail.maintainer}
				</td>
				<shiro:hasPermission name="dev:vehicledetail:devVehicleDetail:edit"><td>
    				<a href="${ctx}/dev/vehicledetail/devVehicleDetail/form?id=${devVehicleDetail.id}">修改</a>
					<a href="${ctx}/dev/vehicledetail/devVehicleDetail/delete?id=${devVehicleDetail.id}" onclick="return confirmx('确认要删除该车辆相关记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>