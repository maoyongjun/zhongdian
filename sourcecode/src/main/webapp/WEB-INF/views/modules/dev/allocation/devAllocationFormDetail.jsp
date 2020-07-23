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
		<li><a href="${ctx}/dev/allocation/devAllocation/">设备调拨列表</a></li>
		<li class="active"><a href="${ctx}/dev/allocation/devAllocation/form?id=${devAllocation.id}">设备调拨<shiro:hasPermission name="dev:allocation:devAllocation:edit">${not empty devAllocation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dev:allocation:devAllocation:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="devAllocation" action="${ctx}/dev/allocation/devAllocation/save" method="post" class="form-horizontal">
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
		<div class="form-actions">
			<shiro:hasPermission name="dev:allocation:devAllocation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

	<div>

		<ul class="nav nav-tabs">
			<li class="active"><a href="#">调拨细项列表</a></li>
			<shiro:hasPermission name="dev:allocationdetail:devAllocationDetail:edit"><li><a href="${ctx}/dev/allocationdetail/devAllocationDetail/formByProjectOfDev?allocationId=${devAllocation.id}&projectId=${devAllocation.projectCheckoutId}">调拨细项添加</a></li></shiro:hasPermission>
		</ul>
		<div style="display: none">
			<form:form id="searchForm" modelAttribute="devAllocationDetail" action="${ctx}/dev/allocationdetail/devAllocationDetail/" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			</form:form>
		</div>
		<sys:message content="${message}"/>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr>
<%--				<th>序号</th>--%>
				<th>设备id</th>
				<th>设备名称</th>
				<shiro:hasPermission name="dev:allocationdetail:devAllocationDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="devAllocationDetail">
				<tr>
<%--					<td>--%>
<%--							${devAllocationDetail.itemNumber}--%>
<%--					</td>--%>
					<td><a href="${ctx}/dev/allocationdetail/devAllocationDetail/formByProjectOfDev?id=${devAllocationDetail.id}&projectId=${devAllocation.projectCheckoutId}">
							${devAllocationDetail.devId}
					</a>
					</td>
					<td>
							${devAllocationDetail.devName}
					</td>
					<shiro:hasPermission name="dev:allocationdetail:devAllocationDetail:edit"><td>
						<a href="${ctx}/dev/allocationdetail/devAllocationDetail/formByProjectOfDev?id=${devAllocationDetail.id}&projectId=${devAllocation.projectCheckoutId}">修改</a>
						<a href="${ctx}/dev/allocationdetail/devAllocationDetail/deleteRedirectFormDetail?id=${devAllocationDetail.id}" onclick="return confirmx('确认要删除该调拨细项吗？', this.href)">删除</a>
					</td></shiro:hasPermission>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>

	</div>
</body>
</html>