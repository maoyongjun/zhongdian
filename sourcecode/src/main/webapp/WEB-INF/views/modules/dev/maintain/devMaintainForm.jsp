<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备保养管理</title>
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
	<div>${devId}</div>
	<div>${devName}</div>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dev/maintain/devMaintain/">设备保养列表</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="devMaintain" action="${ctx}/dev/maintain/devMaintain/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">设备id：</label>
			<div class="controls">
				<form:input path="devId" htmlEscape="false" maxlength="64" class="input-xlarge " value="${devId}"  disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保养时间：</label>
			<div class="controls">
				<input name="date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${devMaintain.date}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保养人：</label>
			<div class="controls">
				<form:input path="maintainBy" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备名称：</label>
			<div class="controls">
				<form:input path="devName" htmlEscape="false" maxlength="255" class="input-xlarge " value="${devName}" disabled="true"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dev:maintain:devMaintain:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>