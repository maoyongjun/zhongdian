<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style>
        #uploadImg{ font-size:12px; overflow:hidden; position:absolute}
        #picture{ position:absolute; z-index:100; margin-left:-180px; font-size:60px;opacity:0;filter:alpha(opacity=0); margin-top:-5px;}
    </style>
    <script language="javascript" type="text/javascript">

        $(document).ready(function() {
            $("#picture").change(function() {
                var current_pic=this.files[0];
                preview_picture(current_pic)
                uploadFile();
            });

        });
        //上传图片
        function uploadFile() {
            $("#msg").text("");
            var data = $("form").serialize();
            $.ajax({
                url:"${ctx}/dev/contract/devContract/save_photo",
                type: 'POST',
                cache: false,
                data: new FormData($("form")[0]),
                processData: false,
                contentType: false,
                success:function (result) {
                    $("#addPic").text("继续添加");
                    $("#msg").text(data.msg);
                    if(result.status==0){
                        $("#msg").css("color","green");
                    }else{
                        $("#msg").css("color","red");
                    }
                    var uuid =  $("#uuid").text();
                    $("#"+uuid+" .deletePic").append("<span style=\"display: none\">"+result.picName+"</span>");
                    $("#"+uuid+" .deletePic").show();
                    $("#"+uuid+" .deletePic").click(function () {
                        var picName = $("#"+uuid+" .deletePic span").text();
                        deletePhoto(picName);
                        $("#"+uuid).remove();
                    });

                }
            });
        }
        function deletePhoto(picName) {
            console.log(picName);
            $.ajax({
                url:"${ctx}/dev/contract/devContract/deletePhoto",
                type: 'POST',
                data: {"picName":picName},
                dataType: "json",
                success:function (result) {
                    console.log(result);
                }
            });
        }
        
        //预览图片
        function preview_picture(pic) {
            var r=new FileReader();
            r.readAsDataURL(pic);
            r.onload=function (){
                var mydate = new Date();
                var uuid = "pic"+mydate.getDay()+ mydate.getHours()+ mydate.getMinutes()+mydate.getSeconds()+mydate.getMilliseconds();
                var html =" <div id="+uuid+" style=\"width: 100px;height: 150px;margin: 0px 20px;float: left\">\n" +
                    "        <span style=\"position: absolute;margin-left: 30px;margin-top: 90px; display: none\" class='deletePic' >删除</span>\n" +
                    "        <img src=\""+this.result+"\" width=\"100px\" height=\"150px\"/>\n" +
                    "    </div>";
                $("#imgs").append(html).show();
                $("#uuid").text(uuid);
                $("#"+uuid+" img").click(function(){
                    var _this = $(this);//将当前的pimg元素作为_this传入函数
                    imgShow("#outerdiv", "#innerdiv", "#bigimg",_this);
                });
            };

        }
        //查看大图
        function imgShow(outerdiv, innerdiv, bigimg, _this){
            var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
            $(bigimg).attr("src", src);//设置#bigimg元素的src属性

            /*获取当前点击图片的真实大小，并显示弹出层及大图*/
            $("<img/>").attr("src", src).load(function(){
                var windowW = $(window).width();//获取当前窗口宽度
                var windowH = $(window).height();//获取当前窗口高度
                var realWidth = this.width;//获取图片真实宽度
                var realHeight = this.height;//获取图片真实高度
                var imgWidth, imgHeight;
                var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

                if(realHeight>windowH*scale) {//判断图片高度
                    imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
                    imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
                    if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
                        imgWidth = windowW*scale;//再对宽度进行缩放
                    }
                } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
                    imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
                    imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度
                } else {//如果图片真实高度和宽度都符合要求，高宽不变
                    imgWidth = realWidth;
                    imgHeight = realHeight;
                }
                $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放

                var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
                var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
                $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
                $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
            });

            $(outerdiv).click(function(){//再次点击淡出消失弹出层
                $(this).fadeOut("fast");
            });
        }

    </script>

</head>
<body>

<div class="content" style="width: 100%;height: 100%">
<form enctype="multipart/form-data">

    <span></span>
    <div>
    <span id="uploadImg">

    <input type="file" id="picture" size="1" name="picture" >
    <a  id="addPic" href="#">上传图片</a></span>
    </div>
    <span id="msg" style="margin-left: 60px;"></span>

</form>
<div id="imgs">
</div>
    <div style="display: none">

        <div style="width: 100px;height: 150px;margin: 0px 20px;float: left">
            <span style="position: absolute;margin-left: 20px;margin-top: -20px;" onclick="alert('删除')">删除</span>
            <span style="position: absolute;margin-left: 60px;margin-top: -20px;" onclick="alert('大图')">大图</span>
            <img src="aaa" width="100px" height="150px"/>
        </div>
        <div style="width: 100px;height: 150px;margin: 0px 20px;float: left">
            <span style="position: absolute;margin-left: 20px;margin-top: -20px;" onclick="alert('删除')">删除</span>
            <span style="position: absolute;margin-left: 60px;margin-top: -20px;" onclick="alert('大图')">大图</span>
            <img src="aaa" width="100px" height="150px"/>
        </div>
        <div id="uuid"></div>
    </div>

    <div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
        <div id="innerdiv" style="position:absolute;">
            <img id="bigimg" style="border:5px solid #fff;" src="" />
        </div>
    </div>
</div>
</body>
</html>
