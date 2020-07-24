<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>评价基本信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {

        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a>评价基本信息列表</a></li>
</ul>
<%--@elvariable id="pjProdBase" type=""--%>
<jsp:useBean id="pjProdBase" class="com.thinkgem.jeesite.modules.pj.entity.prodbase.PjProdBase" scope="request"/>
<form:form id="searchForm" modelAttribute="pjProdBase" action="${ctf}/app/raterbyEvalCorrectList" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>标题：</label>
            <form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>被评价者：</label>
            <sys:treeselect id="raterbyId" name="raterbyId" value="${pjProdBase.raterbyId}" labelName="raterby.name" labelValue="${pjProdBase.raterby.name}"
                            title="用户" url="/sys/user/treeDataAll" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>标题</th>
        <th>被评价者</th>
        <th>发布日期</th>
        <shiro:hasPermission name="pj:prodbase:pjProdBase:edit"><th>操作</th></shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="pjProdBase">
        <tr>

            <td><a href="${ctf}/app/raterbyEvalCorrect?raterbyId=${pjProdBase.raterbyId}&note4=${pjProdBase.createDate}&note3=${pjProdBase.raterby.name}">
                    ${pjProdBase.title}
            </a></td>
            <td>
                    ${pjProdBase.raterby.name}
            </td>
            <td>
                <fmt:formatDate value="${pjProdBase.createDate}" pattern="yyyy-MM-dd"/>
            </td>
            <shiro:hasPermission name="pj:prodbase:pjProdBase:edit"><td>
                <a href="${ctf}/app/raterbyEvalCorrect?raterbyId=${pjProdBase.raterbyId}&note4=${pjProdBase.createDate}&note3=${pjProdBase.raterby.name}">修正</a>
             </td></shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>