<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价细则信息管理</title>
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
		<li class="active"><a href="${ctx}/pj/prodchild/pjProdChild/">评价子表列表</a></li>
		<shiro:hasPermission name="pj:prodchild:pjProdChild:edit"><li><a href="${ctx}/pj/prodchild/pjProdChild/form">评价子表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pjProdChild" action="${ctx}/pj/prodchild/pjProdChild/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>评价细则：</label>
				<sys:treeselect id="valueDetailId" name="valueDetailId" value="${pjProdChild.valueDetailId}" labelName="pjValueDetails.name" labelValue="${pjProdChild.pjValueDetails.name}"
					title="评价细则" url="/pj/details/pjValueDetails/treeDataAll" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
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
				<th style="width: 70%">评价细则</th>
				<th>分值</th>
				<th>创建日期</th>
				<shiro:hasPermission name="pj:prodchild:pjProdChild:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pjProdChild">
			<tr>
				<td>
					<a href="${ctx}/pj/prodchild/pjProdChild/form?id=${pjProdChild.id}">
						${pjProdChild.pjValueDetails.name}
					</a>
				</td>

				<td>
					${pjProdChild.realScore}
				</td>

				<td>
					<fmt:formatDate value="${pjProdChild.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="pj:prodchild:pjProdChild:edit"><td>
    				<a href="${ctx}/pj/prodchild/pjProdChild/form?id=${pjProdChild.id}">修改</a>
					<a href="${ctx}/pj/prodchild/pjProdChild/delete?id=${pjProdChild.id}" onclick="return confirmx('确认要删除该评价细则信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>