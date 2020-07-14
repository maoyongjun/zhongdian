<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>担当金基数管理</title>
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
		<li><a href="${ctx}/pj/money/pjCommitmentMoney/">担当金基数列表</a></li>
		<li class="active"><a href="${ctx}/pj/money/pjCommitmentMoney/form?id=${pjCommitmentMoney.id}">担当金基数<shiro:hasPermission name="pj:money:pjCommitmentMoney:edit">${not empty pjCommitmentMoney.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pj:money:pjCommitmentMoney:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pjCommitmentMoney" action="${ctx}/pj/money/pjCommitmentMoney/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">偏离值范围：</label>
			<div class="controls">
				<form:input path="range" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">担当金基数：</label>
			<div class="controls">
				<form:input path="base" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">范围值左：</label>
			<div class="controls">
				<form:input path="leftNum" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">范围值右：</label>
			<div class="controls">
				<form:input path="rightNum" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pj:money:pjCommitmentMoney:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>