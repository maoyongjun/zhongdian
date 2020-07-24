<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>担当金汇总表</title>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/bootstrap/3.3.7/css/bootstrap3min.css">

    <style>
        label {
            font-size: 13px;
            font-weight: normal;
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

        .container {
            width: 100%;
            margin: 10px 0;
            padding-left: 2%;

        }
    </style>
</head>
<body>

<div id="hengshu">

    <ul class="nav nav-tabs">
        <li class="active"><a>担当金汇总表</a></li>
    </ul>


    <div class="container" style=" background-color: #f5f5f5;">

        <label>日期：</label>

        <input id="beginInDate" name="beginInDate" type="text" readonly="readonly" maxlength="20"
               class="input-medium Wdate"
               value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM"/>"
               style="height: 30px;margin-top:10px;"
               onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/> -
        <input id="endInDate" name="endInDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
               value="<fmt:formatDate value="${testData.endInDate}" pattern="yyyy-MM"/>"
               style="height: 30px;margin-top:10px;"
               onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>

        <button onclick="showTable()" class="btn btn-default" style="height: 32px;margin-left: 4%;">查看</button>
    </div>

    <hr>

    <button class="btn btn-success" style="margin-left: 2%" onclick="toExcel()">导出Excel</button>
    <div id="table-container" style="padding: 4px 2%">

    </div>

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
        // console.log(dataStr);
        var urlStr = '${ctf}/app/getMoneySummaryTable';
        $.ajax({
            url: urlStr,
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: dataStr,
            async: false,
            success: function (result) {
                // console.log(JSON.stringify(result));
                var inner = paintPLTable(result.raterMoneyTotalMap2, result.raterbyMap2, "table2", "奋斗行为担当金统计");

                inner += paintPLTable(result.raterMoneyTotalMap5, result.raterbyMap5, "table5", "事业担当特征担当金统计");

                out.html(inner);
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }

    function paintPLTable(raterMoneyTotalMap, raterbyMap, tableId, title) {

        var raterNameArr = Object.keys(raterMoneyTotalMap);
        var raterbyNameArr = Object.keys(raterbyMap);

        var inner = "<table id='" + tableId + "' class='table table-bordered'><caption>" + title + "</caption><tr><th style=''></th><th style='width: 20%;' colspan='2'>评价人员</th>";
        for (var i in raterNameArr) {
            inner += "<th>" + raterNameArr[i] + "</th>";
        }
        inner += "</tr><th>被评价人员</th><th colspan='2'>最大偏离次数</th>";
        for (var i in raterNameArr) {
            inner += "<td>" + raterMoneyTotalMap[raterNameArr[i]].maxTimes + "</td>";
        }
        inner += "</tr>";

        for (var i in raterbyNameArr) {
            var raterbyName = raterbyNameArr[i];
            inner += "<tr><td rowspan='2'>" + raterbyName + "</td><td rowspan='2'>" + raterbyMap[raterbyName].avgScore.toFixed(7) + "</td><td>偏离率</td>";
            for (var j in raterNameArr) {
                var raterName = raterNameArr[j];
                var raterPllInfo = raterbyMap[raterbyName].raterMoneyInfoMap[raterName];
                if (raterPllInfo) {
                    if (raterPllInfo.maxPl) {
                        inner += "<td style='background-color: #fad2aa'>" + toPercent(raterPllInfo.pll) + "</td>";
                    } else {
                        inner += "<td>" + toPercent(raterPllInfo.pll) + "</td>";
                    }
                } else {
                    inner += "<td></td>";
                }

            }
            inner += "</tr><tr><td>偏离惩罚</td>";
            for (var j in raterNameArr) {
                var raterName = raterNameArr[j];
                var raterPllInfo = raterbyMap[raterbyName].raterMoneyInfoMap[raterName];
                if (raterPllInfo) {
                    inner += "<td>" + raterPllInfo.punishMoney.toFixed(2) + "</td>";
                } else {
                    inner += "<td></td>";
                }
            }
            inner += "</tr>";
        }
        inner += "<tr><td colspan='3'>惩罚金合计</td>";
        for (var i in raterNameArr) {
            var raterName = raterNameArr[i];
            var raterPllInfo = raterMoneyTotalMap[raterName];
            var cfjSum = raterPllInfo.cfjSum;
            if (cfjSum) {
                inner += "<td>" + raterPllInfo.cfjSum.toFixed(2) + "</td>";
            } else {
                inner += "<td></td>";
            }
        }
        inner += "</tr><tr><td style='background-color: #fad2aa' colspan='3'>最大偏离担当金</td>";
        for (var i in raterNameArr) {
            var raterName = raterNameArr[i];
            var raterPllInfo = raterMoneyTotalMap[raterName];
            var maxCfj = raterPllInfo.maxCfj;
            if (maxCfj) {
                inner += "<td style='background-color: #fad2aa'>" + raterPllInfo.maxCfj.toFixed(2) + "</td>";
            } else {
                inner += "<td style='background-color: #fad2aa'></td>";
            }
        }
        inner += "</tr></table>";

        return inner;

    }

    //小数转换百分数
    function toPercent(point) {
        var str = Number(point * 100).toFixed(2);
        str += "%";
        return str;
    }

    function toExcel() {
        var beginInDate = $("#beginInDate").val();
        var endInDate = $("#endInDate").val();
        var dateStr = "";
        if (beginInDate !== "" && endInDate !== "") {
            dateStr = beginInDate + '至' + endInDate;
        }

        $('#table2').table2excel({
            exclude: '.noExcl',                  //标记不导出行的CSS，用到td上会导致后面的td前移
            name: 'ExcelDocumentName',       	//导出的Excel文档的名称
            filename: dateStr + '奋斗行为特征担当金汇总表',                //生成文件名
            fileext: 'xls',                      //导出文件后缀，似乎也没什么用，IE保存没有后缀名
            preserveColors: true,               //保留颜色
            exclude_img: true,                  //是否导出图片
            exclude_links: true,                //是否导出链接
            exclude_inputs: true                //是否导出文本框内容
        });

        $('#table5').table2excel({
            exclude: '.noExcl',                  //标记不导出行的CSS，用到td上会导致后面的td前移
            name: 'ExcelDocumentName',       	//导出的Excel文档的名称
            filename: dateStr + '事业担当特征担当金汇总表',                //生成文件名
            fileext: 'xls',                      //导出文件后缀，似乎也没什么用，IE保存没有后缀名
            preserveColors: true,               //保留颜色
            exclude_img: true,                  //是否导出图片
            exclude_links: true,                //是否导出链接
            exclude_inputs: true                //是否导出文本框内容
        });
    }




</script>
</body>
</html>