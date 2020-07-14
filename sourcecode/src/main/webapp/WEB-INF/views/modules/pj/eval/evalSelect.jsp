<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>评价选择</title>
    <meta name="decorator" content="default"/>
    <style>

        html, body {
            height: 100%;
            width: 100%;
            margin: 0;
            padding: 0;
        }

        .eval-container {
            /*border: 1px solid gray;*/
            padding: 2%;
            height: 86%;
            overflow-y: scroll;
        }

        .rateTitle {
            height: 22px;
            width: 400px;
            margin-bottom: 8px;
            padding: 2px 8px;
            border-radius: 2px;
            outline: none;
            border: 1px solid #999999;
        }

        label {
            display: inline-block;
            font-size: 18px;
        }

        .optionBtnContainer {
            position: absolute;
            right: 6%;
            display: inline-block;
        }


        .item-container {
            /*padding: 20px;*/
        }

        .eval-item {
            border-radius: 10px;
            box-shadow: 1px 1px 2px #888888;
            border: 1px solid lightgray;
            padding: 20px;
            margin: 10px 0;
        }

        .details-container {
            max-height: 300px;
            overflow-y: scroll;
        }

        .details-container > p {
            /*border: 1px solid red;*/
            width: 40%;
            overflow: hidden;
            white-space: nowrap;
            display: inline-block;
            margin-left: 20px;
        }


    </style>
</head>
<body>

<sys:message content="${message}"/>

<h3>评价选择</h3>
<div class="eval-container">

    <div class="control-group">
        <label style="">被评价者：</label>
        <div class="controls" style="display: inline-block">
            <sys:treeselect id="raterbyId" name="raterbyId" value="${pjProdParent.raterbyId}" labelName="raterby.name"
                            labelValue="${pjProdParent.raterby.name}"
                            title="用户" url="/sys/user/treeDataAll" cssClass="required" allowClear="true"
                            notAllowSelectParent="true"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>

<%--        <label style="margin-left: 5%;">标题：</label>--%>
        <div class="optionBtnContainer">
<%--            <input id="rateTitle" class="rateTitle" placeholder="XX年X月XX的价值评价" />--%>
<%--            <span class="help-inline"><font color="red">*</font> </span>--%>

            <button class="btn btn-primary" onclick="addRaterBox()">添加评价人</button>
            <button class="btn btn-danger"  onclick="delRaterBox()">删除评价人</button>
        </div>
    </div>


    <div id="evals" class="item-container">

    </div>

    <div style="text-align: center;margin-top: 20px;">
        <button class="btn btn-primary" onclick="rateCommit()">发布</button>
    </div>


</div>


<script type="text/javascript">

    var RATER_COUNT = 0;

    $(function () {
        console.log("hahahaha");
        addRaterBox();
    });

    function seletcUserFrame(elementId, elementName) {
        top.$.jBox.open("iframe:/jeesite/a/tag/treeselect?url=" + encodeURIComponent("/sys/user/treeDataAll") + "&module=&checked=&extId=&isAll=", "选择用户", 300, 420, {
            ajaxData: {selectIds: $("#" + elementId).val()},
            buttons: {"确定": "ok", "清除": "clear", "关闭": true},
            submit: function (v, h, f) {
                if (v == "ok") {
                    var tree = h.find("iframe")[0].contentWindow.tree;
                    var ids = [], names = [], nodes = [];
                    if ("" == "true") {
                        nodes = tree.getCheckedNodes(true);
                    } else {
                        nodes = tree.getSelectedNodes();
                    }
                    for (var i = 0; i < nodes.length; i++) {
                        if (nodes[i].isParent) {
                            top.$.jBox.tip("不能选择父节点（" + nodes[i].name + "）请重新选择。");
                            return false;
                        }
                        ids.push(nodes[i].id);
                        names.push(nodes[i].name);
                        break;
                    }
                    $("#" + elementId).val(ids.join(",").replace(/u_/ig, ""));
                    $("#" + elementName).val(names.join(","));
                } else if (v == "clear") {
                    $("#" + elementId).val("");
                    $("#" + elementName).val("");
                }
                if (typeof raterIdTreeselectCallBack == 'function') {
                    raterIdTreeselectCallBack(v, h, f);
                }
            },
            loaded: function (h) {
                $(".jbox-content", top.document).css("overflow-y", "hidden");
            }
        });
    }

    function seletcCateFrame(elementId, elementName) {
        console.log("elementId:" + elementId + ",elementName:" + elementName);
        top.$.jBox.open("iframe:/jeesite/a/tag/treeselect?url=" + encodeURIComponent("/pj/category/pjValueCategory/treeDataStatus") + "&module=&checked=true&extId=&isAll=", "选择评价大项", 300, 420, {
            ajaxData: {selectIds: $("#" + elementId).val()},
            buttons: {"确定": "ok", "清除": "clear", "关闭": true},
            submit: function (v, h, f) {
                if (v == "ok") {
                    console.log("v==ok");
                    var tree = h.find("iframe")[0].contentWindow.tree;
                    var ids = [], names = [], nodes = [];
                    nodes = tree.getCheckedNodes(true);
                    // if ("" == "true") {
                    // 	nodes = tree.getCheckedNodes(true);
                    // } else {
                    // 	nodes = tree.getSelectedNodes();
                    // }
                    console.log("nodes.length: " + nodes.length);
                    for (var i = 0; i < nodes.length; i++) {
                        if (nodes[i].isParent) {
                            top.$.jBox.tip("不能选择父节点（" + nodes[i].name + "）请重新选择。");
                            return false;
                        }
                        ids.push(nodes[i].id);
                        names.push(nodes[i].name);
                    }
                    console.log("ids: " + ids);
                    $("#" + elementId).val(ids.join(",").replace(/u_/ig, ""));
                    $("#" + elementName).val(names.join(","));
                } else if (v == "clear") {
                    $("#" + elementId).val("");
                    $("#" + elementName).val("");
                }
                if (typeof raterIdTreeselectCallBack == 'function') {
                    raterIdTreeselectCallBack(v, h, f);
                }
            },
            loaded: function (h) {
                $(".jbox-content", top.document).css("overflow-y", "hidden");
            }
        });
    }

    function addRaterBox() {
        RATER_COUNT += 1;
        var out = $('#evals');
        var inner = out.html();

        var addStr = ["<div id=\"item" + RATER_COUNT + "\" class=\"eval-item\">",
            "    <div>",
            "        <label>评价者：</label>",
            "        <div class=\"controls\" style=\"display: inline-block;\">",
            "            <div class=\"input-append\">",
            "                <input id=\"raterId" + RATER_COUNT + "Id\" name='' class=\"required\" type=\"hidden\" value=\"\">",
            "                <input id=\"raterId" + RATER_COUNT + "Name\" onclick='seletcUserFrame(\"raterId" + RATER_COUNT + "Id\",\"raterId" + RATER_COUNT + "Name\")' name='' readonly=\"readonly\" type=\"text\" value=\"\"",
            "                       data-msg-required=\"\" class=\"required\" style=\"\"><a id=\"raterId" + RATER_COUNT + "Button\" href=\"javascript:\"",
            "                                                                         class=\"btn  \" style=\"\">&nbsp;<i",
            "                    class=\"icon-search\"></i>&nbsp;</a>&nbsp;&nbsp;",
            "            </div>",
            "            <span class=\"help-inline\"><font color=\"red\">*</font> </span>",
            "        </div>",

            "        <label style=\"margin-left: 5%;\">选择评价大项：</label>",
            "        <div class=\"controls\" style=\"display: inline-block;\">",
            "            <div class=\"input-append\">",
            "                <input id=\"valueCate" + RATER_COUNT + "Id\" name=\"valueCate" + RATER_COUNT + "\" class=\"required\" type=\"hidden\" value=\"\">",
            "                <input id=\"valueCate" + RATER_COUNT + "Name\" onclick='seletcCateFrame(\"valueCate" + RATER_COUNT + "Id\",\"valueCate" + RATER_COUNT + "Name\")'  name=\"\" readonly=\"readonly\" type=\"text\" value=\"点击选择大项\" data-msg-required=\"\"",
            "                       class=\"required\" style=\"\">",
            "                <a id=\"valueCate" + RATER_COUNT + "Button\" href=\"javascript:\" class=\"btn hide\" style=\"\">&nbsp;<i class=\"icon-search\"></i>&nbsp;</a>&nbsp;&nbsp;",
            "            </div>",
            "            <button onclick=\"getDetials(\'detailsItem" + RATER_COUNT + "\',\'valueCate" + RATER_COUNT + "Id\')\" class=\"btn btn-success\" style=\"margin-bottom: 10px;\">",
            "                获取细则",
            "            </button>",
            "        </div>",

            "        <hr>",
            "        <div id=\"detailsItem" + RATER_COUNT + "\" class=\"details-container\">",
            "        </div>",
            "    </div>",
            "</div>"
        ].join("");

        // inner += addStr;

        // console.log("inner: " + inner.trim());
        // out.html(inner);

        out.append(addStr);
    }

    //删除评价人
    function delRaterBox() {
        if (RATER_COUNT <= 1) {
            return;
        }
        var itemId = "#item" + RATER_COUNT;
        $(itemId).remove();
        --RATER_COUNT;
    }

    //获取细则
    function getDetials(detailsItemId, valueCateId) {
        var out = $('#' + detailsItemId);
        var dataStr = {};
        dataStr.cateIds = $('#' + valueCateId).val();
        dataStr = JSON.stringify(dataStr);
        console.log(dataStr);
        $.ajax({
            url: "/jeesite/a/pj/eval/getDetailsListByCate",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: dataStr,
            async: false,
            success: function (result) {
                var outlist = result.objectList;
                var inner = "";
                for (var i in outlist) {
                    var inMap = outlist[i];
                    var cateName = inMap.cateName;
                    var inList = inMap.inList;
                    inner += "<h4>" + cateName + "</h4><br>";
                    for (var j in inList) {
                        var detail = inList[j];
                        var id = detail.id;
                        var name = detail.name;
                        var random = Math.floor(Math.random()*10);//生成0-9的随机数
                        if(j%7==3||random>7){
                            inner += "<p><input type='checkbox' name='checkbox" + detailsItemId + "' value='" + id + "' checked='checked' />" + name + "</p>";
                        }else{
                            inner += "<p><input type='checkbox' name='checkbox" + detailsItemId + "' value='" + id + "' />" + name + "</p>";
                        }
                    }
                    inner += "<hr/>";
                }
                out.html(inner);
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }

    /**
     * 判断数组中是否有重复的数值
     * @param arr
     * @returns {boolean}
     */
    function isRepeat(arr){
        var hash = {};
        for(var i in arr) {
            if(hash[arr[i]]){ //hash 哈希
                return true;
            }
            hash[arr[i]] = true;
        }
        return false;
    }

    /**
     * 检查并获取提交数据  data : {"raterbyId":"", "title":"", "evals":[ {"raterId":"", "details":["detailId0","detailId1","detailId2"]}, {"raterId":"", "details":["detailId0","detailId1","detailId2"]},...]}
     */
    function getCheckCommitData() {
        var data = {};
        data.raterbyId = $('#raterbyIdId').val();
        // data.title = $('#rateTitle').val();
        if(data.raterbyId.trim() === ""){
            $("#messageBox").text("输入有误，请先更正。");
            alert("被评价人不能为空！");
            return;
        }
        // if(data.title.trim() === ""){
        //     alert("标题不能为空！");
        //     return;
        // }
        var evals = [];
        var raterArr = [];
        for (var i = 1; i <= RATER_COUNT; i++) {
            var rateInfo = {};
            rateInfo.raterId = $('#raterId' + i + "Id").val();
            rateInfo.bigCate = $("#valueCate" + i + "Id").val();
            if(rateInfo.raterId === ""){
                alert("评价人不能为空！");
                return;
            }
            raterArr[i-1] = rateInfo.raterId;
            var checkName = "checkbox" + "detailsItem" + i;
            rateInfo.details = getRateDetails(checkName);
            if(rateInfo.details.length===0){
                alert("请选择评价细则！");
                return;
            }
            evals[i - 1] = rateInfo;
        }

        if(isRepeat(raterArr)){
            alert("评价人不能重复的");
            return;
        }

        data.evals = evals;

        return data;
    }

    function rateCommit() {
        var data = getCheckCommitData();
        var dataStr = JSON.stringify(data);

        $.ajax({
            url: "/jeesite/a/pj/eval/publish",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: dataStr,
            async: false,
            success: function (result) {
                console.log(result);
                if(result.code==="ok"){
                    alert("发布成功");
                    window.location.reload();//刷新当前页面
                }

            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });


    }

    function getRateDetails(checkName) {   //基础表导出
        var obj = document.getElementsByName(checkName);
        var rateDetails = [];
        for (var i in obj) {
            if (obj[i].checked) {
                rateDetails.push(obj[i].value);
            }
        }
        return rateDetails;
    }


</script>
</body>
</html>