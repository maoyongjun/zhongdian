<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>担当金汇总表</title>
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
            text-align: center;
        }


    </style>
</head>
<body>
<h3 style="text-align: center">担当金汇总表</h3>

<div class="container">

    <label style="margin-left: 8%">日期：</label>

    <input id="beginInDate" name="beginInDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
           value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM"/>"
           style="height: 30px;margin-top:10px;"
           onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/> -
    <input id="endInDate" name="endInDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
           value="<fmt:formatDate value="${testData.endInDate}" pattern="yyyy-MM"/>"
           style="height: 30px;margin-top:10px;"
           onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>

    <button onclick="showTable()" class="btn btn-default" style="height: 32px;margin-left: 4%;">查看</button>
    <hr>
</div>

<div id="table-container" style="padding: 0 10%">

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
        beginDate = (beginDate !== "") ? (beginDate + "-01") : beginDate;


        if (endDate !== "") {
            var year = endDate.split("-")[0];
            var month = endDate.split("-")[1];
            endDate = endDate + "-" + new Date(year, month, 0).getDate();
        }
        data.beginDate = beginDate;
        data.endDate = endDate;

        var dataStr = JSON.stringify(data);
        console.log(dataStr);
        var urlStr = '${ctx}/pj/eval/getMoneySummaryTable';
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

    function paintPLTable(result) {
        var raterMoneyTotalMap = result.raterMoneyTotalMap;
        var raterbyMap = result.raterbyMap;
        var raterNameArr = Object.keys(raterMoneyTotalMap);
        var raterbyNameArr = Object.keys(raterbyMap);

        var inner = "<table class='table table-bordered'><tr><th style='width: 10%;'></th><th style='width: 20%;' colspan='2'>评价人员</th>";
        for(var i in raterNameArr){
            inner+="<th>"+raterNameArr[i]+"</th>";
        }
        inner+="</tr><th>被评价人员</th><th colspan='2'>最大偏离次数</th>";
        for(var i in raterNameArr){
            inner+="<td>"+raterMoneyTotalMap[raterNameArr[i]].maxTimes+"</td>";
        }
        inner+="</tr>";

        for(var i in raterbyNameArr){
            var raterbyName = raterbyNameArr[i];
            inner+="<tr><td rowspan='2'>"+raterbyName+"</td><td rowspan='2'>"+raterbyMap[raterbyName].avgScore.toFixed(6)+"</td><td>偏离率</td>";
            for(var j in raterNameArr){
                var raterName = raterNameArr[j];
                var raterPllInfo = raterbyMap[raterbyName].raterMoneyInfoMap[raterName];
                if(raterPllInfo){
                    if(raterPllInfo.maxPl){
                        inner+="<td style='background-color: #fad2aa'>"+toPercent(raterPllInfo.pll)+"</td>";
                    }else{
                        inner+="<td>"+toPercent(raterPllInfo.pll)+"</td>";
                    }
                }else{
                    inner+="<td></td>";
                }

            }
            inner+="</tr><tr><td>偏离惩罚</td>";
            for(var j in raterNameArr){
                var raterName = raterNameArr[j];
                var raterPllInfo = raterbyMap[raterbyName].raterMoneyInfoMap[raterName];
                if(raterPllInfo){
                    inner+="<td>"+raterPllInfo.punishMoney.toFixed(2)+"</td>";
                }else{
                    inner+="<td></td>";
                }
            }
            inner+="</tr>";
        }
        inner+="<tr><td colspan='3'>惩罚金合计</td>";
        for(var i in raterNameArr){
            var raterName = raterNameArr[i];
            var raterPllInfo = raterMoneyTotalMap[raterName];
            var cfjSum = raterPllInfo.cfjSum;
            if(cfjSum){
                inner+="<td>"+raterPllInfo.cfjSum.toFixed(2)+"</td>";
            }else{
                inner+="<td></td>";
            }
        }
        inner+="</tr><tr style='background-color: #fad2aa '><td colspan='3'>最大偏离担当金</td>";
        for(var i in raterNameArr){
            var raterName = raterNameArr[i];
            var raterPllInfo = raterMoneyTotalMap[raterName];
            var maxCfj = raterPllInfo.maxCfj;
            if(maxCfj){
                inner+="<td>"+raterPllInfo.maxCfj.toFixed(2)+"</td>";
            }else{
                inner+="<td></td>";
            }
        }
        inner+="</tr></table>";

        return inner;

    }

    //小数转换百分数
    function toPercent(point) {
        var str = Number(point * 100).toFixed(2);
        str += "%";
        return str;
    }


</script>
</body>
</html>