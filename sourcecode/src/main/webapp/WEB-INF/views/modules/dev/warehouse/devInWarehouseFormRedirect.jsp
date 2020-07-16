<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>在库设备管理</title>
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
<%--		<li><a href="${ctx}/dev/warehouse/devInWarehouse/">在库设备列表</a></li>--%>
		<li class="active"><a href="#">在库设备<shiro:hasPermission name="dev:warehouse:devInWarehouse:edit">${not empty devInWarehouse.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dev:warehouse:devInWarehouse:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="devInWarehouse" action="${ctx}/dev/warehouse/devInWarehouse/saveRedirect?type=${devInWarehouse.type}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">设备类别：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge " disabled="true">
					<form:options items="${fns:getDictList('dev_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备规格：</label>
			<div class="controls">
				<form:input path="specifications" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备型号：</label>
			<div class="controls">
				<form:input path="model" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备数量：</label>
			<div class="controls">
				<form:input path="count" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购费用：</label>
			<div class="controls">
				<form:input path="cost" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">财务记账票号：</label>
			<div class="controls">
				<form:input path="accountNumber" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购人：</label>
			<div class="controls">
				<form:input path="purchaser" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label">采购项目：</label>
			<div class="controls" >
				<c:if test="${not empty devInWarehouse.id}">
					<form:input path="purchaseProject" htmlEscape="false" maxlength="200" class="input-xlarge " disabled="true"/>
				</c:if>
				<c:if test="${empty devInWarehouse.id}">

					<sys:treeselect id="purchaseProjectId" name="purchaseProjectId" value="${devInWarehouse.purchaseProjectId}" labelName="purchaseProject" labelValue="${devInWarehouse.purchaseProject}"
									title="项目名称" url="/dev/material/devMaterialProject/treeData"  cssClass="" allowClear="true" />
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生产厂家：</label>
			<div class="controls">
				<form:input path="manufacturer" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进场日期：</label>
			<div class="controls">
				<input name="inDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${devInWarehouse.inDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">验收结果：</label>
			<div class="controls">
				<form:input path="acceptanceResults" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">负责人：</label>
			<div class="controls">
				<form:input path="charger" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备所在地：</label>
			<div class="controls">
				<form:input path="location" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同id：</label>
			<div class="controls">
				<form:input path="contractId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group" style="display: none">
			<label class="control-label">入库单id：</label>
			<div class="controls">
				<form:input path="warehouseReceiptId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dev:warehouse:devInWarehouse:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>