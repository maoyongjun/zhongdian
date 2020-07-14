<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>类目维护管理</title>
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
		<li><a href="${ctx}/pj/category/pjValueCategory/">类目列表</a></li>
		<shiro:hasPermission name="pj:category:pjValueCategory:edit"><li><a href="${ctx}/pj/category/pjValueCategory/merge">类目合并</a></li></shiro:hasPermission>
		<li class="active"><a href="${ctx}/pj/category/pjValueCategory/form?id=${pjValueCategory.id}">类目<shiro:hasPermission name="pj:category:pjValueCategory:edit">${not empty pjValueCategory.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pj:category:pjValueCategory:edit">查看</shiro:lacksPermission></a></li>

	</ul><br/>
	<form:form id="inputForm" modelAttribute="pjValueCategory" action="${ctx}/pj/category/pjValueCategory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="statu" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pj_category_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pj:category:pjValueCategory:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>