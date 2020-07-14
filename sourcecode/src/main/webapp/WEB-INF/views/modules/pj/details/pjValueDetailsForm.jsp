<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评定细则管理</title>
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
		<li><a href="${ctx}/pj/details/pjValueDetails/">评定细则列表</a></li>
		<li class="active"><a href="${ctx}/pj/details/pjValueDetails/form?id=${pjValueDetails.id}">评定细则<shiro:hasPermission name="pj:details:pjValueDetails:edit">${not empty pjValueDetails.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pj:details:pjValueDetails:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pjValueDetails" action="${ctx}/pj/details/pjValueDetails/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">类目：</label>

			<sys:treeselect id="cateId" name="cateId" value="${pjValueDetails.pjValueCategory.id}" labelName="pjValueCategory.name" labelValue="${pjValueDetails.pjValueCategory.name}"
							title="类目" url="/pj/category/pjValueCategory/treeData" cssClass="input-xlarge required"  cssStyle="margin-left:20px;" allowClear="true"/>

		</div>
		<div class="control-group">
			<label class="control-label">细则：</label>
			<div class="controls">
				<form:textarea path="name" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分值：</label>
			<div class="controls">
				<form:input path="score" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="note1" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="note2" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="note3" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="note4" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pj:details:pjValueDetails:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>