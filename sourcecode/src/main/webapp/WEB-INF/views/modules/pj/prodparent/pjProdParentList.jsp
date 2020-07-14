<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价信息管理</title>
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
		<li class="active"><a href="${ctx}/pj/prodparent/pjProdParent/">评价信息列表</a></li>
		<shiro:hasPermission name="pj:prodparent:pjProdParent:edit"><li><a href="${ctx}/pj/prodparent/pjProdParent/form">评价信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pjProdParent" action="${ctx}/pj/prodparent/pjProdParent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>评价者：</label>
				<sys:treeselect id="raterId" name="raterId" value="${pjProdParent.raterId}" labelName="rater.name" labelValue="${pjProdParent.rater.name}"
					title="用户" url="/sys/user/treeDataAll" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>被评价者：</label>
				<sys:treeselect id="raterbyId" name="raterbyId" value="${pjProdParent.raterbyId}" labelName="raterby.name" labelValue="${pjProdParent.raterby.name}"
					title="用户" url="/sys/user/treeDataAll" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>状态：</label>
				<form:select path="statu" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('rateStatusType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>评价者</th>
				<th>被评价者</th>
				<th>状态</th>
				<th>创建日期</th>
				<shiro:hasPermission name="pj:prodparent:pjProdParent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pjProdParent">
			<tr>
				<td><a href="${ctx}/pj/prodparent/pjProdParent/form?id=${pjProdParent.id}">
					${pjProdParent.rater.name}
				</a></td>
				<td>
					${pjProdParent.raterby.name}
				</td>
				<td>
					${fns:getDictLabel(pjProdParent.statu, 'rateStatusType', '')}
				</td>
				<td>
					<fmt:formatDate value="${pjProdParent.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pj:prodparent:pjProdParent:edit"><td>
    				<a href="${ctx}/pj/prodparent/pjProdParent/form?id=${pjProdParent.id}">修改</a>
					<a href="${ctx}/pj/prodparent/pjProdParent/delete?id=${pjProdParent.id}" onclick="return confirmx('确认要删除该评价信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>