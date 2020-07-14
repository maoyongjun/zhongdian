<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价汇总信息管理</title>
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
		<li><a href="${ctx}/pj/summary/pjRaterbySummary/">评价汇总信息列表</a></li>
		<li class="active"><a href="${ctx}/pj/summary/pjRaterbySummary/form?id=${pjRaterbySummary.id}">评价汇总信息<shiro:hasPermission name="pj:summary:pjRaterbySummary:edit">${not empty pjRaterbySummary.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pj:summary:pjRaterbySummary:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pjRaterbySummary" action="${ctx}/pj/summary/pjRaterbySummary/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">被评价人：</label>
			<div class="controls">
				<form:input path="raterbyId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评价人：</label>
			<div class="controls">
				<form:input path="raterId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评价人系数：</label>
			<div class="controls">
				<form:input path="reterCoefficient" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类目：</label>
			<div class="controls">
				<form:input path="cateId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分值：</label>
			<div class="controls">
				<form:input path="scores" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">权重占比分值：</label>
			<div class="controls">
				<form:input path="firstCorrectionScores" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">第二次修正分值：</label>
			<div class="controls">
				<form:input path="secondCorrectionScores" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pj:summary:pjRaterbySummary:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>