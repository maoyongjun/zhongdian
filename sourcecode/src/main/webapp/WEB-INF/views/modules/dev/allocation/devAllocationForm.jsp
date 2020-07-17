<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备调拨管理</title>
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
		<li><a href="${ctx}/dev/allocation/devAllocation/?devtype=${devAllocation.devtype}">设备调拨列表</a></li>
		<li class="active"><a href="${ctx}/dev/allocation/devAllocation/form?id=${devAllocation.id}&devtype=${devAllocation.devtype}">设备调拨<shiro:hasPermission name="dev:allocation:devAllocation:edit">${not empty devAllocation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dev:allocation:devAllocation:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="devAllocation" action="${ctx}/dev/allocation/devAllocation/save?devtype=${devAllocation.devtype}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">调拨单名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出库的项目：</label>
			<div class="controls">
				<sys:treeselect id="projectCheckoutId" name="projectCheckoutId" value="${devAllocation.projectCheckoutId}" labelName="projectCheckout" labelValue="${devAllocation.projectCheckout}"
								title="项目名称" url="/dev/material/devMaterialProject/treeData"  cssClass="" allowClear="true"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">调拨时间：</label>
			<div class="controls">
				<input name="allocationDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${devAllocation.allocationDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入库的项目：</label>
			<div class="controls">
				<sys:treeselect id="projectCheckinId" name="projectCheckinId" value="${devAllocation.projectCheckinId}" labelName="projectCheckin" labelValue="${devAllocation.projectCheckin}"
								title="项目名称" url="/dev/material/devMaterialProject/treeData"  cssClass="" allowClear="true"/>
			</div>
		</div>
			<div class="control-group" style="display: none">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:options items="${fns:getDictList('allocation_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">调拨设备类型：</label>
			<div class="controls">
				<form:select path="devtype" class="input-xlarge " disabled="true">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dev_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dev:allocation:devAllocation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

</body>
</html>