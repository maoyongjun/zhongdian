<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>在库设备管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#contentTable  input").change(function() {
				ids = getCheckedIds();
			});
			$("#btnCreateWriteOff").click(function () {
				var projectName = "";
				for(var i = 0;i<ids.length;i++){
					var currentProjectName = $("#"+ids[i]).parent().next().next().next().next().next().next().next().next().next().next().text();
					if(projectName==""){
						projectName = currentProjectName;
					}else{
						if(projectName!=currentProjectName){
							alert("请选择相同项目的设备！");
							return;
						}
					}

				}
				window.open("${ctx}/dev/writeoff/devWriteOff/createWriteOff?ids="+ids+"&devtype=${devInWarehouse.type}","_self");
			});

			$("#btnCreateAllocation").click(function () {
				var projectName = "";
				for(var i = 0;i<ids.length;i++){
					var currentProjectName = $("#"+ids[i]).parent().next().next().next().next().next().next().next().next().next().next().text();
					if(projectName==""){
						projectName = currentProjectName;
					}else{
						if(projectName!=currentProjectName){
							alert("请选择相同项目的设备！");
							return;
						}
					}

				}
				window.open("${ctx}/dev/allocation/devAllocation/createAllocation?ids="+ids+"&devtype=${devInWarehouse.type}","_self");
			});

		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		function getCheckedIds() {
			var checkedIds = $("#contentTable").find("input"), ids = [];
			for (var i = 0; checkedIds && i < checkedIds.length; i++) {
				var obj = $("#contentTable").find("input").eq(i);
				if (obj.is(":checked")) {
					ids.push(obj.attr("id"));//前提是 tbody中每行的checkbox都有id属性
				}
			}
			return ids;

		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">在库设备列表</a></li>
<%--		<shiro:hasPermission name="dev:warehouse:devInWarehouse:edit"><li><a href="${ctx}/dev/warehouse/devInWarehouse/form?type=${devInWarehouse.type}">在库设备添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="devInWarehouse" action="${ctx}/dev/warehouse/devInWarehouse/totallist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备类别：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('dev_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>设备名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
<%--			<li><label>采购人：</label>--%>
<%--				<form:input path="purchaser" htmlEscape="false" maxlength="200" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li><label>采购项目：</label>--%>
<%--				<form:input path="purchaseProject" htmlEscape="false" maxlength="200" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li><label>设备所在地：</label>--%>
<%--				<form:input path="location" htmlEscape="false" maxlength="500" class="input-medium"/>--%>
<%--			</li>--%>
			<li><label>状态：</label>
				<form:select path="devstatus" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('dev_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnCreateWriteOff" class="btn btn-primary"   type="button"  value="生成核销单"/>
				<input id="btnCreateAllocation" class="btn btn-primary"   type="button"  value="生成调拨单"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th></th>
				<th>设备类别</th>
				<th>设备名称</th>
				<th>设备规格</th>
				<th>设备型号</th>
				<th>设备数量</th>
				<th>采购费用</th>
				<th>财务记账票号</th>
				<th>采购人</th>
				<th>采购项目</th>
				<th>生产厂家</th>
				<th>进场日期</th>
				<th>验收结果</th>
				<th>负责人</th>
				<th>设备所在地</th>
				<th>合同id</th>
				<th>入库单id</th>
				<th>状态</th>
				<shiro:hasPermission name="dev:warehouse:devInWarehouse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="devInWarehouse">
			<tr>
				<td><input type="checkbox" name="checkedId" value="${devInWarehouse.id}" id="${devInWarehouse.id}"/></td>
				<c:if test="${devInWarehouse.type=='A4'}">
					<td><a href="${ctx}/dev/warehouse/devInWarehouse/form?id=${devInWarehouse.id}">
							${fns:getDictLabel(devInWarehouse.type, 'dev_type', '')}
					</a></td>
				</c:if>

				<c:if test="${devInWarehouse.type!='A4'}">
					<td><a href="${ctx}/dev/warehouse/devInWarehouse/form?id=${devInWarehouse.id}">
							${fns:getDictLabel(devInWarehouse.type, 'dev_type', '')}
					</a></td>
				</c:if>

				<td>
					${devInWarehouse.name}
				</td>
				<td>
					${devInWarehouse.specifications}
				</td>
				<td>
					${devInWarehouse.model}
				</td>
				<td>
					${devInWarehouse.count}
				</td>
				<td>
					${devInWarehouse.cost}
				</td>
				<td>
					${devInWarehouse.accountNumber}
				</td>
				<td>
					${devInWarehouse.purchaser}
				</td>
				<td>
					${devInWarehouse.purchaseProject}
				</td>
				<td>
					${devInWarehouse.manufacturer}
				</td>
				<td>
					<fmt:formatDate value="${devInWarehouse.inDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${devInWarehouse.acceptanceResults}
				</td>
				<td>
					${devInWarehouse.charger}
				</td>
				<td>
					${devInWarehouse.location}
				</td>
				<td>
					${devInWarehouse.contractId}
				</td>
				<td>
					${devInWarehouse.warehouseReceiptId}
				</td>
				<td>
						${fns:getDictLabel(devInWarehouse.devstatus, 'dev_status', '')}
				</td>
				<shiro:hasPermission name="dev:warehouse:devInWarehouse:edit"><td>
    				<a href="${ctx}/dev/warehouse/devInWarehouse/form?id=${devInWarehouse.id}">修改</a>
					<a href="${ctx}/dev/warehouse/devInWarehouse/delete?id=${devInWarehouse.id}" onclick="return confirmx('确认要删除该在库设备吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>