<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价总分管理</title>
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
		<li><a href="${ctx}/pj/summarytotal/pjSummaryTotal/">评价总分列表</a></li>
		<li class="active"><a href="${ctx}/pj/summarytotal/pjSummaryTotal/form?id=${pjSummaryTotal.id}">评价总分<shiro:hasPermission name="pj:summarytotal:pjSummaryTotal:edit">${not empty pjSummaryTotal.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pj:summarytotal:pjSummaryTotal:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pjSummaryTotal" action="${ctx}/pj/summarytotal/pjSummaryTotal/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">被评价人：</label>
			<div class="controls">
				<sys:treeselect id="raterbyId" name="raterbyId" value="${pjSummaryTotal.raterbyId}" labelName="raterby.name" labelValue="${pjSummaryTotal.raterby.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原始分数：</label>
			<div class="controls">
				<form:input path="baseScore" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">二次修正分数：</label>
			<div class="controls">
				<form:input path="secondScore" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总经理修正分数：</label>
			<div class="controls">
				<form:input path="thirdScore" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">伪奋斗者修正值：</label>
			<div class="controls">
				<form:input path="fakeScore" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">担当值：</label>
			<div class="controls">
				<form:input path="bearScore" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">担当贡献率：</label>
			<div class="controls">
				<form:input path="bearRate" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">note1：</label>
			<div class="controls">
				<form:input path="note1" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">note2：</label>
			<div class="controls">
				<form:input path="note2" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pj:summarytotal:pjSummaryTotal:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>