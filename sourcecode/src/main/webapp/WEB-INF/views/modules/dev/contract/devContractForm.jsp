<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备合同管理</title>
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
		<li><a href="${ctx}/dev/contract/devContract/">设备合同列表</a></li>
		<li class="active"><a href="${ctx}/dev/contract/devContract/form?id=${devContract.id}">设备合同<shiro:hasPermission name="dev:contract:devContract:edit">${not empty devContract.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dev:contract:devContract:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="devContract" action="${ctx}/dev/contract/devContract/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">合同名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购物品：</label>
			<div class="controls">
				<form:input path="purchaseItems" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购人：</label>
			<div class="controls">
				<form:input path="purchaser" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商：</label>
			<div class="controls">
				<form:input path="supplier" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购日期：</label>
			<div class="controls">
				<input name="purchaseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${devContract.purchaseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购价款：</label>
			<div class="controls">
				<form:input path="purchasePrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购数量：</label>
			<div class="controls">
				<form:input path="purchaseNumber" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dev:contract:devContract:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>