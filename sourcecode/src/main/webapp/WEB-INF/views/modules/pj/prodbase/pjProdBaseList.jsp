<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价基本信息管理</title>
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
		<li class="active"><a href="${ctx}/pj/prodbase/pjProdBase/">评价基本信息列表</a></li>
		<shiro:hasPermission name="pj:prodbase:pjProdBase:edit"><li><a href="${ctx}/pj/prodbase/pjProdBase/form">评价基本信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pjProdBase" action="${ctx}/pj/prodbase/pjProdBase/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>被评价者：</label>
				<sys:treeselect id="raterbyId" name="raterbyId" value="${pjProdBase.raterbyId}" labelName="raterby.name" labelValue="${pjProdBase.raterby.name}"
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
				<th>标题</th>
				<th>被评价者</th>
				<th>状态</th>
				<th>创建日期</th>
				<shiro:hasPermission name="pj:prodbase:pjProdBase:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pjProdBase">
			<tr>

				<td><a href="${ctx}/pj/prodbase/pjProdBase/form?id=${pjProdBase.id}">
					${pjProdBase.title}
				</a></td>
				<td>
					${pjProdBase.raterby.name}
				</td>
				<td>
					${fns:getDictLabel(pjProdBase.statu, 'rateStatusType', '')}
				</td>
				<td>
					<fmt:formatDate value="${pjProdBase.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="pj:prodbase:pjProdBase:edit"><td>
    				<a href="${ctx}/pj/prodbase/pjProdBase/form?id=${pjProdBase.id}">修改</a>
					<a href="${ctx}/pj/prodbase/pjProdBase/delete?id=${pjProdBase.id}" onclick="return confirmx('确认要删除该评价基本信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>