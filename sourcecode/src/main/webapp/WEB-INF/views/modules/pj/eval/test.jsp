<div id="item1" class="eval-item">
    <div>
        <label>评价者：</label>
        <div class="controls" style="display: inline-block;">
            <div class="input-append">
                <input id="raterIdId" name="raterId" class="required" type="hidden" value="">
                <input id="raterIdName" name="raterby.name" readonly="readonly" type="text" value=""
                       data-msg-required="" class="required" style=""><a id="raterIdButton" href="javascript:"
                                                                         class="btn  " style="">&nbsp;<i
                    class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;
            </div>
            <script type="text/javascript">
                $("#raterIdButton, #raterIdName").click(function () {

                    if ($("#raterIdButton").hasClass("disabled")) {
                        return true;
                    }

                    top.$.jBox.open("iframe:/jeesite/a/tag/treeselect?url=" + encodeURIComponent("/sys/user/treeDataAll") + "&module=&checked=&extId=&isAll=", "选择用户", 300, 420, {
                        ajaxData: {selectIds: $("#raterIdId").val()},
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
                                $("#raterIdId").val(ids.join(",").replace(/u_/ig, ""));
                                $("#raterIdName").val(names.join(","));
                            } else if (v == "clear") {
                                $("#raterIdId").val("");
                                $("#raterIdName").val("");
                            }
                            if (typeof raterIdTreeselectCallBack == 'function') {
                                raterIdTreeselectCallBack(v, h, f);
                            }
                        },
                        loaded: function (h) {
                            $(".jbox-content", top.document).css("overflow-y", "hidden");
                        }
                    });
                });
            </script>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
        <label style="margin-left: 5%;">选择评价大项：</label>
        <div class="controls" style="display: inline-block;">

            <div class="input-append">
                <input id="valueCate1Id" name="valueCate1" class="required" type="hidden" value="">
                <input id="valueCate1Name" name="" readonly="readonly" type="text" value="点击选择大项" data-msg-required=""
                       class="required" style="">
                <a id="valueCate1Button" href="javascript:" class="btn hide" style="">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;
            </div>
            <script type="text/javascript">
                $("#valueCate1Button, #valueCate1Name").click(function () {

                    if ($("#valueCate1Button").hasClass("disabled")) {
                        return true;
                    }

                    top.$.jBox.open("iframe:/jeesite/a/tag/treeselect?url=" + encodeURIComponent("/pj/category/pjValueCategory/treeDataStatus") + "&module=&checked=true&extId=&isAll=", "选择选择评价大项", 300, 420, {
                        ajaxData: {selectIds: $("#valueCate1Id").val()},
                        buttons: {"确定": "ok", "清除": "clear", "关闭": true},
                        submit: function (v, h, f) {
                            if (v == "ok") {
                                var tree = h.find("iframe")[0].contentWindow.tree;
                                var ids = [], names = [], nodes = [];
                                if ("true" == "true") {
                                    nodes = tree.getCheckedNodes(true);
                                } else {
                                    nodes = tree.getSelectedNodes();
                                }
                                for (var i = 0; i < nodes.length; i++) {
                                    if (nodes[i].isParent) {
                                        continue;
                                    }
                                    if (nodes[i].isParent) {
                                        top.$.jBox.tip("不能选择父节点（" + nodes[i].name + "）请重新选择。");
                                        return false;
                                    }
                                    ids.push(nodes[i].id);
                                    names.push(nodes[i].name);
                                }
                                $("#valueCate1Id").val(ids.join(",").replace(/u_/ig, ""));
                                $("#valueCate1Name").val(names.join(","));
                            } else if (v == "clear") {
                                $("#valueCate1Id").val("");
                                $("#valueCate1Name").val("");
                            }
                            if (typeof valueCate1TreeselectCallBack == 'function') {
                                valueCate1TreeselectCallBack(v, h, f);
                            }
                        },
                        loaded: function (h) {
                            $(".jbox-content", top.document).css("overflow-y", "hidden");
                        }
                    });
                });
            </script>
            <button onclick="getDetials('detailsItem1')" class="btn btn-default" style="margin-bottom: 10px;">
                获取细则
            </button>
        </div>
        <hr>
        <div id="detailsItem1" class="details-container">
        </div>
    </div>
</div>
