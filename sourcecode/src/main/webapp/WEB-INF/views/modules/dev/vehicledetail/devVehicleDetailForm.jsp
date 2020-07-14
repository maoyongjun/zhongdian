<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆相关记录管理</title>
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
		<li><a href="${ctx}/dev/vehicledetail/devVehicleDetail/">车辆相关记录列表</a></li>
		<li class="active"><a href="${ctx}/dev/vehicledetail/devVehicleDetail/form?id=${devVehicleDetail.id}">车辆相关记录<shiro:hasPermission name="dev:vehicledetail:devVehicleDetail:edit">${not empty devVehicleDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dev:vehicledetail:devVehicleDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="devVehicleDetail" action="${ctx}/dev/vehicledetail/devVehicleDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('vehicle_maintain_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">日期：</label>
			<div class="controls">
				<input name="date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${devVehicleDetail.date}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车辆id：</label>
			<div class="controls">
				<form:input path="vehicleId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">部件：</label>
			<div class="controls">
				<form:input path="parts" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">里程数：</label>
			<div class="controls">
				<form:input path="mileage" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">费用：</label>
			<div class="controls">
				<form:input path="cost" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维护人：</label>
			<div class="controls">
				<form:input path="maintainer" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dev:vehicledetail:devVehicleDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>