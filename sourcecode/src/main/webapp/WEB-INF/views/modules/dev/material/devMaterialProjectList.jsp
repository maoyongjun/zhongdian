<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程项目管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dev/material/devMaterialProject/">工程项目列表</a></li>
		<shiro:hasPermission name="dev:material:devMaterialProject:edit"><li><a href="${ctx}/dev/material/devMaterialProject/form">工程项目添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="devMaterialProject" action="${ctx}/dev/material/devMaterialProject/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>排序</th>
				<th>创建时间</th>
				<shiro:hasPermission name="dev:material:devMaterialProject:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/dev/material/devMaterialProject/form?id={{row.id}}">
				{{row.name}}
			</a></td>
			<td>
				{{row.sort}}
			</td>
			<td>
				{{row.createDate}}
			</td>
			<shiro:hasPermission name="dev:material:devMaterialProject:edit"><td>
   				<a href="${ctx}/dev/material/devMaterialProject/form?id={{row.id}}">修改</a>
				<a href="${ctx}/dev/material/devMaterialProject/delete?id={{row.id}}" onclick="return confirmx('确认要删除该工程项目及所有子工程项目吗？', this.href)">删除</a>
				<a href="${ctx}/dev/material/devMaterialProject/form?parent.id={{row.id}}">添加下级工程项目</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>