<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价信息管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/bootstrap/3.3.7/css/bootstrap3min.css">

	<style>
		label{
			font-size: 16px;
		}
		td{
			vertical-align: middle !important;
            border: 0;
		}
		.table th {
			background-image:none;
		}
        .table-bordered>tbody>tr>td, .table-bordered>tbody>tr>th,
        .table-bordered>tfoot>tr>td, .table-bordered>tfoot>tr>th,
        .table-bordered>thead>tr>td, .table-bordered>thead>tr>th{
            border: none;
            border-bottom: 1px solid gainsboro;
            border-right: 1px solid gainsboro;
        }
	</style>
</head>
<body>
	<h3 style="text-align: center">被评价人报表</h3>

	<div class="container">
		<label>被评价者：</label>
		<div class="" style="display: inline-block">
			<sys:treeselect id="raterbyId" name="raterbyId" value="" labelName="raterby.name"
							labelValue="" hideBtn="true" cssStyle="height:30px;margin-top:10px;"
							title="用户" url="/sys/user/treeDataAll" cssClass="input" allowClear="true"
							notAllowSelectParent="true"/>
		</div>

		<label style="margin-left: 8%">日期：</label>

		<input id="beginInDate" name="beginInDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
			   value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM"/>" style="height: 30px;margin-top:10px;"
			   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/> -
		<input id="endInDate" name="endInDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
			   value="<fmt:formatDate value="${testData.endInDate}" pattern="yyyy-MM"/>" style="height: 30px;margin-top:10px;"
			   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>

		<button  onclick="showTable()" class="btn btn-default" style="height: 32px;margin-left: 4%;">获取报表</button>
		<hr>
	</div>

	<div id="table-container" style="padding: 4% 10%">

	</div>

	<script>

		$(function () {
            showTable();
		});

		function showTable() {
			var out = $('#table-container');
			var data = {};
			var beginDate = $('#beginInDate').val();
			var endDate = $('#endInDate').val();
			beginDate = (beginDate!=="")?(beginDate+"-01"):beginDate;

			if(endDate!==""){
				var year = endDate.split("-")[0];
				var month = endDate.split("-")[1];
				endDate = endDate +"-"+new Date(year,month,0).getDate();
			}

			data.raterbyId = $('#raterbyIdId').val();
			data.beginInDate = beginDate;
			data.endInDate = endDate;

			var dataStr = JSON.stringify(data);
			console.log(dataStr);
			var urlStr = '${ctx}/pj/eval/showRaterbyEvalDetailsTable';
			$.ajax({
				url:urlStr,
				type: "POST",
				dataType: "json",
				contentType: "application/json",
				data: dataStr,
				async: false,
				success: function (result) {
					var tableMap = result.tableMap;
					var orangeMap = result.orangeMap;
					out.html("");
					var resultKeyArr = Object.keys(tableMap);
					var inner = "<table class=\"table table-bordered \"><tr style=''><th style='width: 50%;text-align: center;' colspan=\"2\">评价细则</th> ";
					var thFlag = true;
					for(var k in resultKeyArr){
						var cateName = resultKeyArr[k];
						var cateInfo = tableMap[cateName];
						var cateInfoKeyArr = Object.keys(cateInfo);
						var rows = cateInfoKeyArr.length;
						for(var i in cateInfoKeyArr){
							var detailsName = cateInfoKeyArr[i];
							var scoreInfo = cateInfo[detailsName];
							var raterNameArr = Object.keys(scoreInfo);
							if(thFlag){
								for(var j in raterNameArr){	//添加首行标题
									var raterName = raterNameArr[j];
									inner+="<th>"+raterName+"</th>";
								}
								inner+="</tr>";//首行结束
								thFlag = false;
							}
							if(i==0){
								inner+="<tr><td style='width: 10%' rowspan='"+rows+"'>"+cateName+"</td>";//添加左侧类目名称
							}
							inner+="<td>"+detailsName+"</td>";	//添加细则名称
							for(var j in raterNameArr){
								var score = scoreInfo[raterNameArr[j]];
								var isOrange = orangeMap[raterNameArr[j]];
								if(isOrange){
                                    inner+="<td style='background-color: #fad2aa'>"+score+"</td>";
                                }else{
                                    inner+="<td>"+score+"</td>";
                                }

							}
							inner+="</tr>";
						}
					}
					inner+="</table>";
					out.html(inner);
				},
				error: function (e) {
					console.log(e.status);
					console.log(e.responseText);
				}
			});
		}

	</script>
</body>
</html>