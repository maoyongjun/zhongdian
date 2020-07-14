<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价总分管理</title>
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
		<li class="active"><a href="${ctx}/pj/summarytotal/pjSummaryTotal/">评价总分列表</a></li>
		<shiro:hasPermission name="pj:summarytotal:pjSummaryTotal:edit"><li><a href="${ctx}/pj/summarytotal/pjSummaryTotal/form">评价总分添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pjSummaryTotal" action="${ctx}/pj/summarytotal/pjSummaryTotal/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>被评价人：</label>
				<sys:treeselect id="raterbyId" name="raterbyId" value="${pjSummaryTotal.raterbyId}" labelName="raterby.name" labelValue="${pjSummaryTotal.raterby.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>被评价人</th>
				<th>原始分数</th>
				<th>二次修正分数</th>
				<th>总经理修正分数</th>
				<th>伪奋斗者修正值</th>
				<th>担当值</th>
				<th>担当贡献率</th>
				<th>评分日期</th>
				<shiro:hasPermission name="pj:summarytotal:pjSummaryTotal:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pjSummaryTotal">
			<tr>
				<td><a href="${ctx}/pj/summarytotal/pjSummaryTotal/form?id=${pjSummaryTotal.id}">
					${pjSummaryTotal.raterby.name}
				</a></td>
				<td>
					${pjSummaryTotal.baseScore}
				</td>
				<td>
					${pjSummaryTotal.secondScore}
				</td>
				<td>
					${pjSummaryTotal.thirdScore}
				</td>
				<td>
					${pjSummaryTotal.fakeScore}
				</td>
				<td>
					${pjSummaryTotal.bearScore}
				</td>
				<td>
					${pjSummaryTotal.bearRate}
				</td>
				<td>
					<fmt:formatDate value="${pjSummaryTotal.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pj:summarytotal:pjSummaryTotal:edit"><td>
    				<a href="${ctx}/pj/summarytotal/pjSummaryTotal/form?id=${pjSummaryTotal.id}">修改</a>
					<a href="${ctx}/pj/summarytotal/pjSummaryTotal/delete?id=${pjSummaryTotal.id}" onclick="return confirmx('确认要删除该评价总分吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>