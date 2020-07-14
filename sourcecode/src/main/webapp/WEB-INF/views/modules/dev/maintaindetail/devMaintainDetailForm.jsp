<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保养项次管理</title>
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
		<li><a href="${ctx}/dev/maintaindetail/devMaintainDetail/">保养项次列表</a></li>
		<li class="active"><a href="${ctx}/dev/maintaindetail/devMaintainDetail/form?id=${devMaintainDetail.id}">保养项次<shiro:hasPermission name="dev:maintaindetail:devMaintainDetail:edit">${not empty devMaintainDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dev:maintaindetail:devMaintainDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="devMaintainDetail" action="${ctx}/dev/maintaindetail/devMaintainDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">保养记录id：</label>
			<div class="controls">
				<form:input path="maintainId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保养的项次：</label>
			<div class="controls">
				<form:input path="itemNumber" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项次名称：</label>
			<div class="controls">
				<form:input path="itemName" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dev:maintaindetail:devMaintainDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>