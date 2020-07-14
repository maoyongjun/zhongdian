<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>价值评价汇总表</title>
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
<h3 style="text-align: center">价值评价汇总表</h3>

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
        var urlStr = '${ctx}/pj/eval/getTotalTable';
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
        var summaryTotalVoList = result.summaryTotalVoList;
        var inner = "<table class=\"table table-bordered \"><caption>价值评价表</caption><tr><th>姓名</th><th>奋斗值</th><th>奋斗值（修正）</th><th>伪奋斗者修正值</th><th>担当值</th><th>担当贡献率</th></tr>";
        for (var i in summaryTotalVoList) {
            var summary = summaryTotalVoList[i];
            inner += "<tr><td>" + summary.raterbyName + "</td>"
                + "<td>" + summary.baseScore + "</td>"
                + "<td>" + summary.thirdScore + "</td>"
                + "<td>" + summary.fakeScore + "</td>"
                + "<td>" + summary.bearScore + "</td>"
                + "<td>" + summary.bearRate + "</td></tr>";
        }
        inner += "</table>";

        return inner;

    }


</script>
</body>
</html>