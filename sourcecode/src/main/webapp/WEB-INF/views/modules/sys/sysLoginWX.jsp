<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>${fns:getConfig('productName')} 登录</title>
    <meta name="decorator" content="blank"/>
</head>
<body>


<div hidden>

    <form id="loginForm" action="${ctx}/login" method="post">

        <label class="input-label" for="username">登录名</label>
        <input type="text" id="username" name="username" class="input-block-level required" value="${loginName}">
        <label class="input-label" for="password">密码</label>
        <input type="password" id="password" name="password" value="${passWord}" class="input-block-level required">

        <input id="submitBtn" class="btn btn-block login-btn btn-primary" type="submit" value="登 录"/>&nbsp;&nbsp;

    </form>

</div>

<script>

    document.getElementById("submitBtn").click();

</script>

</body>
</html>