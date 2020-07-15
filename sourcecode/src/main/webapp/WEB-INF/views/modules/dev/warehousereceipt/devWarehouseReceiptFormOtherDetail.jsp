<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入库单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
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
		<li><a href="${ctx}/dev/warehousereceipt/devWarehouseReceipt/">入库单列表</a></li>
		<li class="active"><a href="${ctx}/dev/warehousereceipt/devWarehouseReceipt/form?id=${devWarehouseReceipt.id}">入库单<shiro:hasPermission name="dev:warehousereceipt:devWarehouseReceipt:edit">${not empty devWarehouseReceipt.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dev:warehousereceipt:devWarehouseReceipt:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="devWarehouseReceipt" action="${ctx}/dev/warehousereceipt/devWarehouseReceipt/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">入库单名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入库单时间：</label>
			<div class="controls">
				<input name="date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${devWarehouseReceipt.date}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购人：</label>
			<div class="controls">
				<form:input path="purchaser" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">库管：</label>
			<div class="controls">
				<form:input path="warehouseManagement" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审批人：</label>
			<div class="controls">
				<form:input path="approver" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dev:warehousereceipt:devWarehouseReceipt:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>


	<div>
		<ul class="nav nav-tabs">
			<li class="active"><a href="#">在库设备列表</a></li>
			<shiro:hasPermission name="dev:warehouse:devInWarehouse:edit"><li><a href="${ctx}/dev/warehouse/devInWarehouse/formRedirect?type=${devInWarehouse.type}&warehouseReceiptId=${devWarehouseReceipt.id}">在库设备添加</a></li></shiro:hasPermission>
		</ul>
		<div style="display: none">
		<form:form id="searchForm" modelAttribute="devInWarehouse" action="${ctx}/dev/warehouse/devInWarehouse/?type=${devInWarehouse.type}&warehouseReceiptId=${devWarehouseReceipt.id}" method="post" class="breadcrumb form-search" >
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<ul class="ul-form">
				<li><label>设备类别：</label>
					<form:select path="type" class="input-medium" disabled="true">
						<form:options items="${fns:getDictList('dev_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
				<li><label>设备名称：</label>
					<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
				</li>
				<li><label>采购人：</label>
					<form:input path="purchaser" htmlEscape="false" maxlength="200" class="input-medium"/>
				</li>
				<li><label>采购项目：</label>
					<form:input path="purchaseProject" htmlEscape="false" maxlength="200" class="input-medium"/>
				</li>
				<li><label>设备所在地：</label>
					<form:input path="location" htmlEscape="false" maxlength="500" class="input-medium"/>
				</li>
				<li><label>合同id：</label>
					<form:input path="contractId" htmlEscape="false" maxlength="64" class="input-medium"/>
				</li>
				<li><label>入库单id：</label>
					<form:input path="warehouseReceiptId" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<shiro:hasPermission name="dev:warehouse:devInWarehouse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="devInWarehouse">
				<tr>
					<td><a href="${ctx}/dev/warehouse/devInWarehouse/formRedirect?id=${devInWarehouse.id}&type=${devInWarehouse.type}">
							${fns:getDictLabel(devInWarehouse.type, 'dev_type', '')}
					</a></td>
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
					<shiro:hasPermission name="dev:warehouse:devInWarehouse:edit"><td>
						<a href="${ctx}/dev/warehouse/devInWarehouse/formRedirect?id=${devInWarehouse.id}&type=${devInWarehouse.type}">修改</a>
						<a href="${ctx}/dev/warehouse/devInWarehouse/deleteRedirect?id=${devInWarehouse.id}" onclick="return confirmx('确认要删除该在库设备吗？', this.href)">删除</a>
					</td></shiro:hasPermission>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>

	</div>
</body>
</html>