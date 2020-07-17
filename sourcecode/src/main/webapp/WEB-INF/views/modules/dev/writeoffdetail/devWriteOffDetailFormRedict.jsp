<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>核销单明细管理</title>
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
		<li class="active"><a href="#">核销单明细<shiro:hasPermission name="dev:writeoffdetail:devWriteOffDetail:edit">${not empty devWriteOffDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dev:writeoffdetail:devWriteOffDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="devWriteOffDetail" action="${ctx}/dev/writeoffdetail/devWriteOffDetail/saveRedirectDetail" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">设备：</label>
			<div class="controls">
				<c:if test="${devtype eq 'A4'}">
					<sys:treeselect id="devid" name="devid" value="${devAllocationDetail.devid}" labelName="devname" labelValue="${devAllocationDetail.devname}"
									title="设备名称" url="/dev/vehicle/devVehicle/treeData?projectId=${projectId}"  cssClass="" allowClear="true"/>
				</c:if>
				<c:if test="${devtype ne 'A4'}">
					<sys:treeselect id="devid" name="devid" value="${devAllocationDetail.devid}" labelName="devname" labelValue="${devAllocationDetail.devname}"
									title="设备名称" url="/dev/warehouse/devInWarehouse/treeData?projectId=${projectId}"  cssClass="" allowClear="true"/>
				</c:if>
			</div>
		</div>
		<div class="control-group" style="display: none">
			<label class="control-label">核销单id：</label>
			<div class="controls">
				<form:input path="writeoffId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">项目id：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="projectid" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">项目名称：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="projectname" htmlEscape="false" maxlength="300" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="dev:writeoffdetail:devWriteOffDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>