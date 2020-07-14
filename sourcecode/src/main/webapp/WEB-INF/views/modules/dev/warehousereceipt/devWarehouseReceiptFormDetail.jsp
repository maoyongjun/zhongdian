<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入库单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
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
		<li><a href="${ctx}/dev/warehousereceipt/devWarehouseReceipt/">入库单列表</a></li>
		<li class="active"><a href="${ctx}/dev/warehousereceipt/devWarehouseReceipt/form?id=${devWarehouseReceipt.id}">入库单<shiro:hasPermission name="dev:warehousereceipt:devWarehouseReceipt:edit">${not empty devWarehouseReceipt.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dev:warehousereceipt:devWarehouseReceipt:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="devWarehouseReceipt" action="${ctx}/dev/warehousereceipt/devWarehouseReceipt/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">入库单名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入库单时间：</label>
			<div class="controls">
				<input name="date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${devWarehouseReceipt.date}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购人：</label>
			<div class="controls">
				<form:input path="purchaser" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">库管：</label>
			<div class="controls">
				<form:input path="warehouseManagement" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审批人：</label>
			<div class="controls">
				<form:input path="approver" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dev:warehousereceipt:devWarehouseReceipt:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>


	<div>
		<ul class="nav nav-tabs">
			<li class="active"><a href="#">入库单明细</a></li>
			<shiro:hasPermission name="dev:vehicle:devVehicle:edit"><li><a href="${ctx}/dev/vehicle/devVehicle/form?warehouseReceiptId=${devWarehouseReceipt.id}">采购入库车辆添加</a></li></shiro:hasPermission>
		</ul>
		<div style="display: none">
			<form:form id="searchForm" modelAttribute="devVehicle" action="${ctx}/dev/vehicle/devVehicle/" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

			</form:form>
		</div>

		<sys:message content="${message}"/>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr>
				<th>车辆名称</th>
				<th>车辆类型</th>
				<th>车辆所在地</th>
				<th>设备规格</th>
				<th>设备型号</th>
				<th>车辆使用人</th>
				<th>保险日期</th>
				<th>审车日期</th>
				<th>所属项目名称</th>
				<shiro:hasPermission name="dev:vehicle:devVehicle:edit"><th>操作</th></shiro:hasPermission>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="devVehicle">
				<tr>
					<td><a href="${ctx}/dev/vehicle/devVehicle/formForPurchase?id=${devVehicle.id}">
							${devVehicle.name}
					</a></td>
					<td>
							${fns:getDictLabel(devVehicle.type, 'vehicle_type', '')}
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
							${devVehicle.projectName}
					</td>
					<shiro:hasPermission name="dev:vehicle:devVehicle:edit"><td>
						<a href="${ctx}/dev/vehicle/devVehicle/formForPurchase?id=${devVehicle.id}">修改</a>
						<a href="${ctx}/dev/vehicle/devVehicle/deleteRedirectWarehousereceipt?id=${devVehicle.id}" onclick="return confirmx('确认要删除该设备吗？', this.href)">删除</a>
					</td>
					</shiro:hasPermission>

				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>

	</div>
</body>
</html>