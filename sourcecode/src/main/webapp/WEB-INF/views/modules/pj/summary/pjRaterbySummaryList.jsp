<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价汇总信息管理</title>
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
		<li class="active"><a href="${ctx}/pj/summary/pjRaterbySummary/">评价汇总信息列表</a></li>
	</ul>
	<div style="padding-bottom: 20px">
	<form:form id="searchForm" modelAttribute="pjRaterbySummary" action="${ctx}/pj/summary/pjRaterbySummary/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>被评价人：</label>
				<div class="controls" style="display: inline-block">
					<sys:treeselect id="raterbyId" name="raterbyId" value="${pjProdParent.raterbyId}" labelName="raterby.name"
									labelValue="${pjProdParent.raterby.name}"
									title="用户" url="/sys/user/treeDataAll" cssClass="required" allowClear="true"
									notAllowSelectParent="true"/>
				</div>
			</li>
			<li><label>评价人：</label>
				<div class="controls" style="display: inline-block">
					<sys:treeselect id="raterId" name="raterbyId" value="${pjProdParent.raterbyId}" labelName="raterby.name"
									labelValue="${pjProdParent.raterby.name}"
									title="用户" url="/sys/user/treeDataAll" cssClass="required" allowClear="true"
									notAllowSelectParent="true"/>
				</div>
<%--				<form:input path="raterId" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
			</li>
			<li><label>类目：</label>
				<sys:treeselect id="cateId" name="cateId" value="${pjValueDetails.pjValueCategory.id}" labelName="pjValueCategory.name" labelValue="${pjValueDetails.pjValueCategory.name}"
								title="菜系" url="/pj/category/pjValueCategory/treeData" cssClass="input-medium required"  cssStyle="margin-left:20px;" allowClear="true"/>
<%--				<form:input path="cateId" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>被评价人</th>
				<th>评价人</th>
				<th>类目</th>
				<th>生成日期</th>
				<th>分值</th>
				<th>权重占比分值</th>
				<th>第二次修正分值</th>
				<shiro:hasPermission name="pj:summary:pjRaterbySummary:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pjRaterbySummary">
			<tr>
				<td><a href="${ctx}/pj/summary/pjRaterbySummary/form?id=${pjRaterbySummary.id}">
					${pjRaterbySummary.raterbyId}
				</a></td>
				<td>
					${pjRaterbySummary.raterId}
				</td>
				<td>
					${pjRaterbySummary.cateId}
				</td>
				<td>
					<fmt:formatDate value="${pjRaterbySummary.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pjRaterbySummary.scores}
				</td>
				<td>
					${pjRaterbySummary.firstCorrectionScores}
				</td>
				<td>
					${pjRaterbySummary.secondCorrectionScores}
				</td>
				<shiro:hasPermission name="pj:summary:pjRaterbySummary:edit"><td>
<%--    				<a href="${ctx}/pj/summary/pjRaterbySummary/form?id=${pjRaterbySummary.id}">修改</a>--%>
<%--					<a href="${ctx}/pj/summary/pjRaterbySummary/delete?id=${pjRaterbySummary.id}" onclick="return confirmx('确认要删除该评价汇总信息吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>