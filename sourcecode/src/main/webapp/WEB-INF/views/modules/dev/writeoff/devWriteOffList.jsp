<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>核销单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
		<li class="active"><a href="${ctx}/dev/writeoff/devWriteOff/?devtype=${devWriteOff.devtype}">核销单列表</a></li>
		<shiro:hasPermission name="dev:writeoff:devWriteOff:edit"><li><a href="${ctx}/dev/writeoff/devWriteOff/form?devtype=${devWriteOff.devtype}">核销单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="devWriteOff" action="${ctx}/dev/writeoff/devWriteOff/?devtype=${devWriteOff.devtype}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目id：</label>
				<form:input path="projectid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>项目名称：</label>
				<form:input path="projectname" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li><label>核销单名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('writeoff_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>设备类型：</label>
				<form:select path="devtype" class="input-medium" disabled="true">
					<form:options items="${fns:getDictList('dev_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名称</th>
				<th>核销单名称</th>
				<th>申请人</th>
				<th>审核人</th>
				<th>申请时间</th>
				<th>审核时间</th>
				<th>状态</th>
				<th>设备类型</th>
				<shiro:hasPermission name="dev:writeoff:devWriteOff:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devWriteOff">
			<tr>
				<td><a href="${ctx}/dev/writeoff/devWriteOff/formDetail?id=${devWriteOff.id}">
					${devWriteOff.projectname}
				</a>
				</td>
				<td>
					${devWriteOff.name}
				</td>
				<td>
					${devWriteOff.applicant}
				</td>
				<td>
					${devWriteOff.reviewer}
				</td>
				<td>
					<fmt:formatDate value="${devWriteOff.applicantDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${devWriteOff.reviewerDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(devWriteOff.status, 'writeoff_status', '')}
				</td>
				<td>
					${fns:getDictLabel(devWriteOff.devtype, 'dev_type', '')}
				</td>
				<shiro:hasPermission name="dev:writeoff:devWriteOff:edit"><td>
    				<a href="${ctx}/dev/writeoff/devWriteOff/formDetail?id=${devWriteOff.id}">修改</a>
					<a href="${ctx}/dev/writeoff/devWriteOff/delete?id=${devWriteOff.id}" onclick="return confirmx('确认要删除该核销单吗？', this.href)">删除</a>
					<a href="${ctx}/dev/writeoff/devWriteOff/sureWriteOff?id=${devWriteOff.id}&status=2">申请核销</a>
					<a href="${ctx}/dev/writeoff/devWriteOff/sureWriteOff?id=${devWriteOff.id}&status=3">确认核销</a>				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>