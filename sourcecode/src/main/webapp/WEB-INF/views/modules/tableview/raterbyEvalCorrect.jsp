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

<ul class="nav nav-tabs">
    <li class="active"><a>评价信息修正</a></li>
</ul>

<div class="container" hidden>
    <label>被评价者：</label>
    <input id="raterbyIdId" value="${raterbyId}">

    <label style="margin-left: 8%">日期：</label>

    <input id="beginInDate" name="beginInDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
           value="<fmt:formatDate value="${createDate}" pattern="yyyy-MM"/>"
           style="height: 30px;margin-top:10px;"
           onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>

</div>

<h3 style="text-align: center">${raterbyName}评价信息修正</h3>
<hr>

<button class="btn btn-success" style="margin-left: 2%" onclick="toExcel()">导出Excel</button>
<div id="table-container" style="padding: 4px 2%">

</div>


<script>

    $(function () {
        showTable();
    });

    function showTable() {
        var out = $('#table-container');
        var data = {};
        var createDate = $('#beginInDate').val();
        createDate = (createDate !== "") ? (createDate + "-01") : createDate;

        data.raterbyId = $('#raterbyIdId').val();
        data.createDate = createDate;

        var dataStr = JSON.stringify(data);
        // console.log(dataStr);
        var urlStr = '${ctf}/app/getRaterbyEvalCorrect';
        $.ajax({
            url: urlStr,
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: dataStr,
            async: false,
            success: function (result) {
                // console.log(JSON.stringify(result));
                if (result.code === "ok") {
                    var inner = paintPLTable(result);
                    out.html(inner);
                } else if (result.code === "error") {
                    alert(result.msg);
                }
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }

    //小数转换百分数
    function toPercent(point) {
        if (isNaN(point)) {
            return "";
        }
        var str = Number(point * 100).toFixed(2);
        str += "%";
        return str;
    }


    /**
     * 加载表格
     * @param result
     * @returns {string}
     */
    function paintPLTable(result) {
        var xwRMap = result.xwRMap;
        var ddRMap = result.ddRMap;
        var wxSecondScoreMap = result.wxSecondScore;
        var ddSecondScoreMap = result.ddSecondScore;
        var summaryTotalVo = result.summaryTotalVo;
        // console.log(JSON.stringify(result));
        var raterNameArr = Object.keys(xwRMap);
        var inner = "<table id='tableInfo' class=\"table table-bordered \"><tr><th>参与人员</th>";
        for (var i in raterNameArr) {
            inner += "<th>" + raterNameArr[i] + "</th>";
        }
        inner += "<th>修正前</th><th>修正后</th></tr><tr><td>评价行为偏离率</td>";
        for (var i in raterNameArr) {
            var pll = toPercent(xwRMap[raterNameArr[i]]);
            inner += "<td>" + pll + "</td>";
        }
        inner += "<td>" + wxSecondScoreMap.AvgScore + "</td><td>" + wxSecondScoreMap.secondScore + "</td>";
        inner += "</tr><tr><td>评价担当偏离率</td>";
        for (var i in raterNameArr) {
            var pll = toPercent(ddRMap[raterNameArr[i]]);
            inner += "<td>" + pll + "</td>";
        }
        inner += "<td>" + ddSecondScoreMap.AvgScore + "</td><td>" + ddSecondScoreMap.secondScore + "</td>";
        inner += "</tr></table><hr>";

        if (summaryTotalVo) {
            // var secondScore = summaryTotalVo.secondScore;
            inner += "<input hidden id='summaryTotalId' value='" + summaryTotalVo.id + "' />";
            inner += "<table class=\"table table-bordered \"><caption>价值评价表</caption><tr><th>姓名</th><th>奋斗值</th><th>奋斗值修正</th><th>总经理修正</th><th>伪奋斗者修正值</th><th>担当值</th><th>担当贡献率</th></tr>";
            inner += "<tr>" + "<td>" + summaryTotalVo.raterbyName + "</td>"
                + "<td>" + summaryTotalVo.baseScore + "</td>"
                + "<td id='secondScoreTd'>" + summaryTotalVo.secondScore + "</td>"
                + "<td id='thirdScoreTd'>" + summaryTotalVo.thirdScore + "</td>"
                + "<td>" + summaryTotalVo.fakeScore + "</td>"
                + "<td>" + summaryTotalVo.bearScore + "</td>"
                + "<td>" + summaryTotalVo.bearRate + "</td></tr></table><hr>";

            inner += "<lable>总经理修正：</lable><input id='zjlScore' style='height: 30px;margin-top: 10px;' type='number' step='0.01' max='" + 20 + "' min='" + -20 + "' placeholder='-20% ~ 20%' />" +
                "<button onclick='updateThirdScore()' style='margin-left: 20px;' class='btn btn-primary'>确定</button>";

            inner += " <input class='btn btn-default' type='button'  value='返 回' onclick='history.go(-1)' />";
        }
        return inner;

    }

    function updateThirdScore() {
        var secondScore = parseFloat($('#secondScoreTd').text());
        var zjlxzr = parseFloat($('#zjlScore').val()) / 100;
        var zjlScore = secondScore * (1 + zjlxzr);

        if (zjlxzr < -0.2 || zjlxzr > 0.2) {
            alert("修正值不在范围内");
            return;
        }

        var data = {};
        data.id = $('#summaryTotalId').val();
        data.thirdScore = zjlScore;

        var dataStr = JSON.stringify(data);
        // console.log(dataStr);
        var urlStr = '${ctf}/app/updateThirdScore';
        $.ajax({
            url: urlStr,
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: dataStr,
            async: false,
            success: function (result) {
                if (result.code === "ok") {
                    $('#thirdScoreTd').html(zjlScore.toFixed(7));
                }
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }

    function toExcel() {
        $('#tableInfo').table2excel({
            exclude: '.noExcl',                  //标记不导出行的CSS，用到td上会导致后面的td前移
            name: 'ExcelDocumentName',       	//导出的Excel文档的名称
            filename: 'FileName',                //生成文件名
            fileext: 'xls',                      //导出文件后缀，似乎也没什么用，IE保存没有后缀名
            preserveColors: true,               //保留颜色
            // exclude_img: false,                  //是否导出图片
            // exclude_links: false,                //是否导出链接
            // exclude_inputs: false                //是否导出文本框内容
        });
    }

</script>
</body>
</html>