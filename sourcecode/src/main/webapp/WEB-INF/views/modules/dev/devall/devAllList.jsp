<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理</title>
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
		<li class="active"><a href="${ctx}/dev/devall/devAll/">设备列表</a></li>
		<shiro:hasPermission name="dev:devall:devAll:edit"><li><a href="${ctx}/dev/devall/devAll/form">设备添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="devAll" action="${ctx}/dev/devall/devAll/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dev_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li><label>项目id：</label>
				<form:input path="projectid" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>所在地：</label>
				<form:input path="location" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>设备状态：</label>
				<form:select path="devstatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dev_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>卡车类型：</label>
				<form:select path="vechicletype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('vehicle_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>类型</th>
				<th>名称</th>
				<th>规格</th>
				<th>型号</th>
				<th>数量</th>
				<th>费用</th>
				<th>财务编号</th>
				<th>采购人</th>
				<th>项目id</th>
				<th>项目名称</th>
				<th>供应商</th>
				<th>创建时间</th>
				<th>验收结果</th>
				<th>负责人</th>
				<th>所在地</th>
				<th>设备状态</th>
				<th>卡车类型</th>
				<shiro:hasPermission name="dev:devall:devAll:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devAll">
			<tr>
				<td><a href="${ctx}/dev/devall/devAll/form?id=${devAll.id}">
					${fns:getDictLabel(devAll.type, 'dev_type', '')}
				</a></td>
				<td>
					${devAll.name}
				</td>
				<td>
					${devAll.specifications}
				</td>
				<td>
					${devAll.model}
				</td>
				<td>
					${devAll.count}
				</td>
				<td>
					${devAll.cost}
				</td>
				<td>
					${devAll.accountnumber}
				</td>
				<td>
					${devAll.purchaser}
				</td>
				<td>
					${devAll.projectid}
				</td>
				<td>
					${devAll.projectname}
				</td>
				<td>
					${devAll.manufacturer}
				</td>
				<td>
					<fmt:formatDate value="${devAll.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${devAll.acceptanceresults}
				</td>
				<td>
					${devAll.charger}
				</td>
				<td>
					${devAll.location}
				</td>
				<td>
					${fns:getDictLabel(devAll.devstatus, 'dev_status', '')}
				</td>
				<td>
					${fns:getDictLabel(devAll.vechicletype, 'vehicle_type', '')}
				</td>
				<shiro:hasPermission name="dev:devall:devAll:edit"><td>
    				<a href="${ctx}/dev/devall/devAll/form?id=${devAll.id}">修改</a>
					<a href="${ctx}/dev/devall/devAll/delete?id=${devAll.id}" onclick="return confirmx('确认要删除该设备吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>