<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆管理管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dev/vehicle/devVehicle/form?id=${devVehicle.id}">采购车辆入库信息<shiro:hasPermission name="dev:vehicle:devVehicle:edit">${not empty devVehicle.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dev:vehicle:devVehicle:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="devVehicle" action="${ctx}/dev/vehicle/devVehicle/saveRedirectWarehousereceipt?status=${devVehicle.status}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">车辆名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车辆类型：</label>
			<div class="controls">
				<form:select path="vechicleType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('vehicle_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车辆所在地：</label>
			<div class="controls">
				<form:input path="location" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备规格：</label>
			<div class="controls">
				<form:input path="specifications" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备型号：</label>
			<div class="controls">
				<form:input path="model" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车辆使用人：</label>
			<div class="controls">
				<form:input path="useBy" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保险日期：</label>
			<div class="controls">
				<input name="insuranceDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${devVehicle.insuranceDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审车日期：</label>
			<div class="controls">
				<input name="reviewDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${devVehicle.reviewDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge " disabled="true">
					<form:options items="${fns:getDictList('dev_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属项目：</label>
			<div class="controls">
				<sys:treeselect id="projectId" name="projectId" value="${devVehicle.projectId}" labelName="projectName" labelValue="${devVehicle.projectName}"
								title="项目名称" url="/dev/material/devMaterialProject/treeData"  cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" style="display: none">入库单id：</label>
			<div class="controls">
				<form:input path="warehouseReceiptId" name="warehouseReceiptId" htmlEscape="false" maxlength="64" class="input-xlarge "   type="hidden"   value="${devVehicle.warehouseReceiptId}"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dev:vehicle:devVehicle:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>