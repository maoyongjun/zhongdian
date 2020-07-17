<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>核销单管理</title>
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
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dev/writeoff/devWriteOff/?devtype=${devWriteOff.devtype}">核销单列表</a></li>
		<li class="active"><a href="${ctx}/dev/writeoff/devWriteOff/form?id=${devWriteOff.id}&devtype=${devWriteOff.devtype}">核销单<shiro:hasPermission name="dev:writeoff:devWriteOff:edit">${not empty devWriteOff.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dev:writeoff:devWriteOff:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="devWriteOff" action="${ctx}/dev/writeoff/devWriteOff/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<sys:treeselect id="projectid" name="projectid" value="${devWriteOff.projectid}" labelName="projectname" labelValue="${devWriteOff.projectname}"
								title="项目名称" url="/dev/material/devMaterialProject/treeData"  cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">核销单名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请人：</label>
			<div class="controls">
				<form:input path="applicant" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核人：</label>
			<div class="controls">
				<form:input path="reviewer" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请时间：</label>
			<div class="controls">
				<input name="applicantDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${devWriteOff.applicantDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核时间：</label>
			<div class="controls">
				<input name="reviewerDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${devWriteOff.reviewerDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group"  style="display: none">
			<label class="control-label">状态：</label>
			<div class="controls" >
				<form:select path="status" class="input-xlarge ">
					<form:options items="${fns:getDictList('writeoff_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dev:writeoff:devWriteOff:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<div>
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/dev/writeoffdetail/devWriteOffDetail/">核销单明细列表</a></li>
			<shiro:hasPermission name="dev:writeoffdetail:devWriteOffDetail:edit"><li><a href="${ctx}/dev/writeoffdetail/devWriteOffDetail/formRedict?writeoffId=${devWriteOff.id}&devtype=${devWriteOff.devtype}&projectId=${devWriteOff.projectid}">核销单明细添加</a></li></shiro:hasPermission>
		</ul>
		<form:form id="searchForm" modelAttribute="devWriteOffDetail" action="${ctx}/dev/writeoffdetail/devWriteOffDetail/?writeoffId=${devWriteOff.id}" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		</form:form>
		<sys:message content="${message}"/>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr>
				<th>设备id</th>
				<th>设备名称</th>
				<th>核销单id</th>
				<shiro:hasPermission name="dev:writeoffdetail:devWriteOffDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="devWriteOffDetail">
				<tr>
					<td><a href="${ctx}/dev/writeoffdetail/devWriteOffDetail/formRedict?id=${devWriteOffDetail.id}&writeoffId=${devWriteOff.id}">
							${devWriteOffDetail.devid}
					</a></td>
					<td>
							${devWriteOffDetail.devname}
					</td>
					<td>
							${devWriteOffDetail.writeoffId}
					</td>
					<shiro:hasPermission name="dev:writeoffdetail:devWriteOffDetail:edit"><td>
						<a href="${ctx}/dev/writeoffdetail/devWriteOffDetail/formRedict?id=${devWriteOffDetail.id}&devtype=${devWriteOff.devtype}&projectId=${devWriteOff.projectid}">修改</a>
						<a href="${ctx}/dev/writeoffdetail/devWriteOffDetail/deleteRedirectDetail?id=${devWriteOffDetail.id}" onclick="return confirmx('确认要删除该核销单明细吗？', this.href)">删除</a>
					</td></shiro:hasPermission>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>

	</div>
</body>
</html>