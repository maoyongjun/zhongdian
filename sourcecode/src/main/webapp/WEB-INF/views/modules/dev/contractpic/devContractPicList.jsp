<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同图片管理</title>
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
		<li class="active"><a href="${ctx}/dev/contractpic/devContractPic/">合同图片列表</a></li>
		<shiro:hasPermission name="dev:contractpic:devContractPic:edit"><li><a href="${ctx}/dev/contractpic/devContractPic/form">合同图片添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="devContractPic" action="${ctx}/dev/contractpic/devContractPic/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>id：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>图片路径：</label>
				<form:input path="picpath" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>图片名称：</label>
				<form:input path="picname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>原名称：</label>
				<form:input path="orgname" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li><label>合同id：</label>
				<form:input path="contractid" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>图片路径</th>
				<th>图片名称</th>
				<th>原名称</th>
				<shiro:hasPermission name="dev:contractpic:devContractPic:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devContractPic">
			<tr>
				<td><a href="${ctx}/dev/contractpic/devContractPic/form?id=${devContractPic.id}">
					${devContractPic.id}
				</a></td>
				<td>
					${devContractPic.picpath}
				</td>
				<td>
					${devContractPic.picname}
				</td>
				<td>
					${devContractPic.orgname}
				</td>
				<shiro:hasPermission name="dev:contractpic:devContractPic:edit"><td>
    				<a href="${ctx}/dev/contractpic/devContractPic/form?id=${devContractPic.id}">修改</a>
					<a href="${ctx}/dev/contractpic/devContractPic/delete?id=${devContractPic.id}" onclick="return confirmx('确认要删除该合同图片吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>