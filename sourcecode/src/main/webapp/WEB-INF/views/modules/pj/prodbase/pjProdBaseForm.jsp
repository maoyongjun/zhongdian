<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价基本信息管理</title>
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
		<li><a href="${ctx}/pj/prodbase/pjProdBase/">评价基本信息列表</a></li>
		<li class="active"><a href="${ctx}/pj/prodbase/pjProdBase/form?id=${pjProdBase.id}">评价基本信息<shiro:hasPermission name="pj:prodbase:pjProdBase:edit">${not empty pjProdBase.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pj:prodbase:pjProdBase:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pjProdBase" action="${ctx}/pj/prodbase/pjProdBase/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" hidden>
			<label class="control-label">评价标识：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">评价者：</label>--%>
<%--			<div class="controls">--%>
<%--				<sys:treeselect id="raterId" name="raterId" value="${pjProdBase.raterId}" labelName="rater.name" labelValue="${pjProdBase.rater.name}"--%>
<%--					title="用户" url="/sys/user/treeDataAll" cssClass="required" allowClear="true" notAllowSelectParent="true"/>--%>
<%--				<span class="help-inline"><font color="red">*</font> </span>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="control-group">
			<label class="control-label">被评价者：</label>
			<div class="controls">
				<sys:treeselect id="raterbyId" name="raterbyId" value="${pjProdBase.raterbyId}" labelName="raterby.name" labelValue="${pjProdBase.raterby.name}"
					title="用户" url="/sys/user/treeDataAll" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="statu" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('rateStatusType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">note1：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="note1" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">note2：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="note2" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">note3：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="note3" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">note4：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="note4" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="pj:prodbase:pjProdBase:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>



	<hr>
	<h4 style="margin-left: 5%;">评价者列表</h4><br>
	<div style="height: 300px;overflow-y: scroll;padding: 0 5%;">

		<table class="table table-bordered table-striped ">
			<tr>
				<th>评价人</th><th>被评价人</th><th>状态</th><th>日期</th>
			</tr>
			<c:forEach items="${pjParentList}" var="parent">
				<tr>
					<td>
						<a href="${ctx}/pj/prodparent/pjProdParent/form?id=${parent.id}">${parent.rater.name}</a>
					</td>
					<td>
						${parent.raterby.name}
					</td>
					<td>
						${fns:getDictLabel(parent.statu, 'rateStatusType', '')}
					</td>
					<td>
						<fmt:formatDate value="${parent.createDate}" pattern="yyyy-MM"/>
					</td>
				</tr>
			</c:forEach>


		</table>

	</div>

</body>
</html>