<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调拨细项管理</title>
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
		<li class="active"><a href="#">调拨细项<shiro:hasPermission name="dev:allocationdetail:devAllocationDetail:edit">${not empty devAllocationDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dev:allocationdetail:devAllocationDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="devAllocationDetail" action="${ctx}/dev/allocationdetail/devAllocationDetail/saveRedirectFormDetail" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" style="display: none">
			<label class="control-label">调拨单id：</label>
			<div class="controls">
				<form:input path="allocationId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">序号：</label>
			<div class="controls">
				<form:input path="itemNumber" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备：</label>
			<div class="controls">
				<sys:treeselect id="devId" name="devId" value="${devAllocationDetail.devId}" labelName="devName" labelValue="${devAllocationDetail.devName}"
								title="设备名称" url="/dev/vehicle/devVehicle/treeData?projectId=${projectId}"  cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dev:allocationdetail:devAllocationDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>