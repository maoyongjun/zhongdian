<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程物资管理</title>
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
		<li><a href="${ctx}/dev/material/devMaterialDetail/">工程物资列表</a></li>
		<li class="active"><a href="${ctx}/dev/material/devMaterialDetail/form?id=${devMaterialDetail.id}">工程物资<shiro:hasPermission name="dev:material:devMaterialDetail:edit">${not empty devMaterialDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dev:material:devMaterialDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="devMaterialDetail" action="${ctx}/dev/material/devMaterialDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">物资名称：</label>
			<div class="controls">
				<form:input path="materialName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量：</label>
			<div class="controls">
				<form:input path="count" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<sys:treeselect id="projectId" name="projectId" value="${devMaterialDetail.projectId}" labelName="name" labelValue="${devMaterialDetail.project.name}"
								title="项目名称" url="/dev/material/devMaterialProject/treeData"  cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dev:material:devMaterialDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>