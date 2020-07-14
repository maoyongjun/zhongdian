<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价细则信息管理</title>
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
<%--		<li><a href="${ctx}/pj/prodchild/pjProdChild/">评价子表列表</a></li>--%>
		<li class="active"><a href="${ctx}/pj/prodchild/pjProdChild/form?id=${pjProdChild.id}">评价子表<shiro:hasPermission name="pj:prodchild:pjProdChild:edit">${not empty pjProdChild.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pj:prodchild:pjProdChild:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pjProdChild" action="${ctx}/pj/prodchild/pjProdChild/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>

		<div class="control-group">
			<label class="control-label">评价细则：</label>
			<div class="controls">
				<sys:treeselect id="valueDetailId" name="valueDetailId" value="${pjProdChild.valueDetailId}" labelName="pjValueDetails.name" labelValue="${pjProdChild.pjValueDetails.name}"
								title="评价细则" url="/pj/details/pjValueDetails/treeDataAll" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">状态：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:select path="statu" class="input-xlarge ">--%>
<%--					<form:option value="" label=""/>--%>
<%--					<form:options items="${fns:getDictList('rateStatusType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
<%--				</form:select>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="control-group">
			<label class="control-label">分值：</label>
			<div class="controls">
				<form:input path="realScore" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">日期：</label>
			<div class="controls">
				<fmt:formatDate value="${pjProdChild.createDate}" pattern="yyyy-MM-dd" var="formatDate"/>
				<form:input path="createDate"  value="${formatDate}" htmlEscape="false" class="input-xlarge " readonly="true"/>
			</div>
		</div>

<%--		<div class="control-group">--%>
<%--			<label class="control-label">备注1：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="note1" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">备注2：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="note2" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">备注3：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="note3" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">备注4：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="note4" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="pj:prodchild:pjProdChild:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>