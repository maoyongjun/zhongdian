<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>评价信息修正</title>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/bootstrap/3.3.7/css/bootstrap3min.css">

    <style>
        label {
            font-size: 16px;
        }

        td {
            vertical-align: middle !important;
            border: 0;
        }

        .table th {
            background-image: none;
        }

        .table-bordered > tbody > tr > td, .table-bordered > tbody > tr > th,
        .table-bordered > tfoot > tr > td, .table-bordered > tfoot > tr > th,
        .table-bordered > thead > tr > td, .table-bordered > thead > tr > th {
            border: none;
            border-bottom: 1px solid gainsboro;
            border-right: 1px solid gainsboro;
        }
    </style>
</head>
<body>
<h3 style="text-align: center">评价信息修正</h3>

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
           value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM"/>"
           style="height: 30px;margin-top:10px;"
           onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
    <button onclick="showTable()" class="btn btn-default" style="height: 32px;margin-left: 4%;">查看</button>
    <hr>
</div>

<div id="table-container" style="padding: 0 10%">

</div>

<script>

    $(function () {
        // showTable();
    });

    function showTable() {
        var out = $('#table-container');
        var data = {};
        var createDate = $('#beginInDate').val();
        createDate = (createDate !== "") ? (createDate + "-01") : createDate;

        data.raterbyId = $('#raterbyIdId').val();
        data.createDate = createDate;

        var dataStr = JSON.stringify(data);
        console.log(dataStr);
        var urlStr = '${ctx}/pj/eval/getRaterbyEvalCorrect';
        $.ajax({
            url: urlStr,
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: dataStr,
            async: false,
            success: function (result) {
                console.log(JSON.stringify(result));
                var inner = paintPLTable(result);

                out.html(inner);
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }

    //小数转换百分数
    function toPercent(point) {
        var str = Number(point * 100).toFixed(2);
        str += "%";
        return str;
    }

    var MIN_XZ_SCORE;
    var MAX_XZ_SCORE;

    function paintPLTable(result) {
        var xwRMap = result.xwRMap;
        var ddRMap = result.ddRMap;
        var wxSecondScoreMap = result.wxSecondScore;
        var ddSecondScoreMap = result.ddSecondScore;
        var summaryTotalVo = result.summaryTotalVo;
        console.log(JSON.stringify(result));
        var raterNameArr = Object.keys(xwRMap);
        var inner = "<table class=\"table table-bordered \"><caption>行为/担当特征偏离值分析</caption><tr><th>参与人员</th>";
        for (var i in raterNameArr) {
            inner += "<th>" + raterNameArr[i] + "</th>";
        }
        inner += "<th>修正前</th><th>修正后</th></tr><tr><td>评价行为偏离率</td>";
        for (var i in raterNameArr) {
            inner += "<td>" + toPercent(xwRMap[raterNameArr[i]]) + "</td>";
        }
        inner += "<td>" + wxSecondScoreMap.AvgScore + "</td><td>" + wxSecondScoreMap.secondScore + "</td>";
        inner += "</tr><tr><td>评价担当偏离率</td>";
        for (var i in raterNameArr) {
            inner += "<td>" + toPercent(ddRMap[raterNameArr[i]]) + "</td>";
        }
        inner += "<td>" + ddSecondScoreMap.AvgScore + "</td><td>" + ddSecondScoreMap.secondScore + "</td>";
        inner += "</tr></table><hr>";

        if (summaryTotalVo) {
            var secondScore = summaryTotalVo.secondScore;
            MIN_XZ_SCORE = Number(secondScore - secondScore * 0.2).toFixed(2);
            MAX_XZ_SCORE = Number(secondScore + secondScore * 0.2).toFixed(2);
            inner += "<input hidden id='summaryTotalId' value='" + summaryTotalVo.id + "' />";
            inner += "<table class=\"table table-bordered \"><caption>价值评价表</caption><tr><th>姓名</th><th>奋斗值</th><th>奋斗值修正</th><th>总经理修正</th><th>伪奋斗者修正值</th><th>担当值</th><th>担当贡献率</th></tr>";
            inner += "<tr>" + "<td>" + summaryTotalVo.raterbyName + "</td>"
                + "<td>" + summaryTotalVo.baseScore + "</td>"
                + "<td>" + summaryTotalVo.secondScore + "</td>"
                + "<td id='thirdScoreTd'>" + summaryTotalVo.thirdScore + "</td>"
                + "<td>" + summaryTotalVo.fakeScore + "</td>"
                + "<td>" + summaryTotalVo.bearScore + "</td>"
                + "<td>" + summaryTotalVo.bearRate + "</td></tr></table><hr>";

            inner += "<p>修改范围：" + MIN_XZ_SCORE + " - " + MAX_XZ_SCORE + "</p>"
            inner += "<lable>总经理修正：</lable><input id='zjlScore' style='height: 30px;margin-top: 10px;' type='number' step='0.01' max='" + MAX_XZ_SCORE + "' min='" + MIN_XZ_SCORE + "' /><button onclick='updateThirdScore()' style='margin-left: 20px;' class='btn btn-default'>确定</button>"
        }
        return inner;

    }

    function updateThirdScore() {
        var zjlScore = Number($('#zjlScore').val()).toFixed(2);
		$('#zjlScore').val(zjlScore);
        if (zjlScore<MIN_XZ_SCORE || zjlScore > MAX_XZ_SCORE) {
			alert("修正值不在范围内");
			return;
        }

		var data = {};
		data.id = $('#summaryTotalId').val();
		data.thirdScore =  zjlScore;

		var dataStr = JSON.stringify(data);
		console.log(dataStr);
		var urlStr = '${ctx}/pj/eval/updateThirdScore';
        $.ajax({
			url: urlStr,
			type: "POST",
			dataType: "json",
			contentType: "application/json",
			data: dataStr,
			async: false,
			success: function (result) {
				if(result.code==="ok"){
					$('#thirdScoreTd').html(zjlScore);
				}
			},
			error: function (e) {
				console.log(e.status);
				console.log(e.responseText);
			}
		})

    }

</script>
</body>
</html>