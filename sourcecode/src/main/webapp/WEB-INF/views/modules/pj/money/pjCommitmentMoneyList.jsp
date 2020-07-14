<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>担当金基数管理</title>
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
		<li class="active"><a href="${ctx}/pj/money/pjCommitmentMoney/">担当金基数列表</a></li>
		<shiro:hasPermission name="pj:money:pjCommitmentMoney:edit"><li><a href="${ctx}/pj/money/pjCommitmentMoney/form">担当金基数添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pjCommitmentMoney" action="${ctx}/pj/money/pjCommitmentMoney/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>担当金基数：</label>
				<form:input path="base" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>偏离值范围</th>
				<th>担当金基数</th>
				<th>范围值左</th>
				<th>范围值右</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th>修改者</th>
				<shiro:hasPermission name="pj:money:pjCommitmentMoney:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pjCommitmentMoney">
			<tr>
				<td><a href="${ctx}/pj/money/pjCommitmentMoney/form?id=${pjCommitmentMoney.id}">
					${pjCommitmentMoney.range}
				</a></td>
				<td>
					${pjCommitmentMoney.base}
				</td>
				<td>
					${pjCommitmentMoney.leftNum}
				</td>
				<td>
					${pjCommitmentMoney.rightNum}
				</td>
				<td>
					${pjCommitmentMoney.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${pjCommitmentMoney.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${pjCommitmentMoney.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pjCommitmentMoney.updateBy.id}
				</td>
				<shiro:hasPermission name="pj:money:pjCommitmentMoney:edit"><td>
    				<a href="${ctx}/pj/money/pjCommitmentMoney/form?id=${pjCommitmentMoney.id}">修改</a>
					<a href="${ctx}/pj/money/pjCommitmentMoney/delete?id=${pjCommitmentMoney.id}" onclick="return confirmx('确认要删除该担当金基数吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>