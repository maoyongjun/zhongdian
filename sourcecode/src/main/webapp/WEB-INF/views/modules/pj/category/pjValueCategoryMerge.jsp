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
		<li class="active"><a href="${ctx}/pj/category/pjValueCategory/form?id=${pjValueCategory.id}">类目合并<shiro:hasPermission name="pj:category:pjValueCategory:edit"></shiro:hasPermission>
		<shiro:hasPermission name="pj:category:pjValueCategory:edit"><li><a href="${ctx}/pj/category/pjValueCategory/form">类目添加</a></li></shiro:hasPermission>
		</ul><br/>

	<form:form id="inputForm" modelAttribute="pjValueCategory" action="${ctx}/pj/category/pjValueCategory/savemerge" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="row">
			<div class="span4" ></div>
			<div class="control-group " >
				<label class="control-label">类目1：</label>
				<sys:treeselect id="id" name="id" value="${pjValueDetails.pjValueCategory.id}" labelName="pjValueCategory.name" labelValue="${pjValueDetails.pjValueCategory.name}"
								title="类目1" url="/pj/category/pjValueCategory/treeData" cssClass="input-medium required"  cssStyle="margin-left:20px;" allowClear="true"/>
			</div>
		</div>
		<div class="row">
			<div class="span5" ></div>
			<div class="span4" style="text-align: center;font-size: 48px;color: #2a8198">
			<i class="icon-arrow-up" ></i>
			</div>
		</div>
		<div class="row">
			<div class="span4" ></div>
			<div class="control-group ">
				<label class="control-label">类目2：</label>
				<sys:treeselect id="note1" name="note1" value="${pjValueDetails.pjValueCategory.id}" labelName="pjValueCategory.name" labelValue="${pjValueDetails.pjValueCategory.name}"
								title="类目2" url="/pj/category/pjValueCategory/treeDataCate2" cssClass="input-medium required"  cssStyle="margin-left:20px;" allowClear="true"/>
			</div>
		</div>
	<div class="row">
		<div class="span4" ></div>
		<div class="form-actions span4">
			<shiro:hasPermission name="pj:category:pjValueCategory:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="合 并"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</div>
	</form:form>
</body>
</html>