<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
	<title>车辆管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var ids =[];
		$(document).ready(function() {
			$("#contentTable  input").change(function() {
				ids = getCheckedIds();
			});
			$("#btnCreateWriteOff").click(function () {
				console.log(ids);
				window.open("${ctx}/dev/writeoff/devWriteOff/createWriteOff?ids="+ids+"&devtype=A4");
			});


		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function getCheckedIds() {
			var checkedIds = $("#contentTable").find("input"), ids = [];
			for (var i = 0; checkedIds && i < checkedIds.length; i++) {
				var obj = $("#contentTable").find("input").eq(i);
				if (obj.is(":checked")) {
					ids.push(obj.attr("id"));//前提是 tbody中每行的checkbox都有id属性
				}
			}
			return ids;

		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dev/vehicle/devVehicle/">车辆管理列表</a></li>
<%--		<shiro:hasPermission name="dev:vehicle:devVehicle:edit"><li><a href="${ctx}/dev/vehicle/devVehicle/form">车辆管理添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="devVehicle" action="${ctx}/dev/vehicle/devVehicle/listByDevStatus?devStatus=${devStatus}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>车辆名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li><label>车辆类型：</label>
				<form:select path="vechicleType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('vehicle_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">

					<c:if test="${devStatus==1}">
					 <form:options items="${fns:getDictList('dev_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</c:if>

					<c:if test="${devStatus==3}">
					 <form:options items="${fns:getDictList('dev_status_writeoff')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</c:if>

				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnCreateWriteOff" class="btn btn-primary"   type="button"  value="生成核销单"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>
<%--					<input type="checkbox" name="checkbox" id="checkAll"/>--%>
				</th>
				<th>车辆名称</th>
				<th>车辆类型</th>
				<th>车辆所在地</th>
				<th>设备规格</th>
				<th>设备型号</th>
				<th>车辆使用人</th>
				<th>保险日期</th>
				<th>审车日期</th>
				<th>状态</th>
				<th>所属项目名称</th>
				<shiro:hasPermission name="dev:vehicle:devVehicle:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devVehicle">
			<tr>
				<td><input type="checkbox" name="checkedId" value="${devVehicle.id}" id="${devVehicle.id}"/></td>
				<td>
					<c:if test="${devStatus==1}">
					<a href="${ctx}/dev/vehicle/devVehicle/form?id=${devVehicle.id}"></c:if>
					${devVehicle.name}
				</a></td>
				<td>
					${fns:getDictLabel(devVehicle.vechicleType, 'vehicle_type', '')}
				</td>
				<td>
					${devVehicle.location}
				</td>
				<td>
					${devVehicle.specifications}
				</td>
				<td>
					${devVehicle.model}
				</td>
				<td>
					${devVehicle.useBy}
				</td>
				<td>
					<fmt:formatDate value="${devVehicle.insuranceDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${devVehicle.reviewDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${devStatus==1}">
						${fns:getDictLabel(devVehicle.status, 'dev_status', '')}
					</c:if>
					<c:if test="${devStatus==3}">
						${fns:getDictLabel(devVehicle.status, 'dev_status_writeoff', '')}
					</c:if>
				</td>
				<td>
					${devVehicle.projectName}
				</td>
<%--				<td>--%>
<%--						${devVehicle.warehouseReceiptId}--%>
<%--				</td>--%>
				<shiro:hasPermission name="dev:vehicle:devVehicle:edit"><td>
					<c:if test="${devStatus==1}">
    				<a href="${ctx}/dev/vehicle/devVehicle/form?id=${devVehicle.id}">修改</a>
					</c:if>
					<a href="${ctx}/dev/vehicledetail/devVehicleDetail/list?id=${devVehicle.id}">维修保养记录</a>
				</td>
				</shiro:hasPermission>			
             </tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>