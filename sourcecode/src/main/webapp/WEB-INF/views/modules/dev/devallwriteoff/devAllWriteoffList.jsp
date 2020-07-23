<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>核销设备管理</title>
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
		<li class="active"><a href="${ctx}/dev/devallwriteoff/devAllWriteoff/">核销设备列表</a></li>
		<shiro:hasPermission name="dev:devallwriteoff:devAllWriteoff:edit"><li><a href="${ctx}/dev/devallwriteoff/devAllWriteoff/form">核销设备添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="devAllWriteoff" action="${ctx}/dev/devallwriteoff/devAllWriteoff/" method="post" class="breadcrumb form-search">
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
			<li><label>项目名称：</label>
				<form:input path="projectname" htmlEscape="false" maxlength="200" class="input-medium"/>
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
				<th>合同id</th>
				<th>入库单id</th>
				<th>设备状态</th>
				<th>卡车类型</th>
				<th>使用人</th>
				<th>保险日期</th>
				<th>审车日期</th>
				<shiro:hasPermission name="dev:devallwriteoff:devAllWriteoff:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devAllWriteoff">
			<tr>
				<td><a href="${ctx}/dev/devallwriteoff/devAllWriteoff/form?id=${devAllWriteoff.id}">
					${fns:getDictLabel(devAllWriteoff.type, 'dev_type', '')}
				</a></td>
				<td>
					${devAllWriteoff.name}
				</td>
				<td>
					${devAllWriteoff.specifications}
				</td>
				<td>
					${devAllWriteoff.model}
				</td>
				<td>
					${devAllWriteoff.count}
				</td>
				<td>
					${devAllWriteoff.cost}
				</td>
				<td>
					${devAllWriteoff.accountnumber}
				</td>
				<td>
					${devAllWriteoff.purchaser}
				</td>
				<td>
					${devAllWriteoff.projectid}
				</td>
				<td>
					${devAllWriteoff.projectname}
				</td>
				<td>
					${devAllWriteoff.manufacturer}
				</td>
				<td>
					<fmt:formatDate value="${devAllWriteoff.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${devAllWriteoff.acceptanceresults}
				</td>
				<td>
					${devAllWriteoff.charger}
				</td>
				<td>
					${devAllWriteoff.location}
				</td>
				<td>
					${devAllWriteoff.contractid}
				</td>
				<td>
					${devAllWriteoff.warehousereceiptid}
				</td>
				<td>
					${fns:getDictLabel(devAllWriteoff.devstatus, 'dev_status', '')}
				</td>
				<td>
					${fns:getDictLabel(devAllWriteoff.vechicletype, 'vehicle_type', '')}
				</td>
				<td>
					${devAllWriteoff.useby}
				</td>
				<td>
					${devAllWriteoff.insurancedate}
				</td>
				<td>
					${devAllWriteoff.reviewdate}
				</td>
				<shiro:hasPermission name="dev:devallwriteoff:devAllWriteoff:edit"><td>
    				<a href="${ctx}/dev/devallwriteoff/devAllWriteoff/form?id=${devAllWriteoff.id}">修改</a>
					<a href="${ctx}/dev/devallwriteoff/devAllWriteoff/delete?id=${devAllWriteoff.id}" onclick="return confirmx('确认要删除该核销设备吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>