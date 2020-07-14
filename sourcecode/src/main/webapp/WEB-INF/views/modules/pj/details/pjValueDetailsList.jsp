<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>评定细则管理</title>
    <meta name="decorator" content="default"/>

    <style>

    </style>

    <script type="text/javascript">
        $(document).ready(function () {

        });

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/pj/details/pjValueDetails/">评定细则列表</a></li>
    <shiro:hasPermission name="pj:details:pjValueDetails:edit">
        <li><a href="${ctx}/pj/details/pjValueDetails/form">评定细则添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="pjValueDetails" action="${ctx}/pj/details/pjValueDetails/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>类目：</label>

            <sys:treeselect id="cateId" name="cateId" value="${pjValueDetails.pjValueCategory.id}" labelName="pjValueCategory.name" labelValue="${pjValueDetails.pjValueCategory.name}"
                            title="菜系" url="/pj/category/pjValueCategory/treeData" cssClass="input-medium required"  cssStyle="margin-left:20px;" allowClear="true"/>

<%--            <form:input path="cateId" htmlEscape="false" class="input-medium"/>--%>
        </li>
        <li><label>细则：</label>
            <form:input path="name" htmlEscape="false" class="input-medium"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>细则</th>
        <th>类目</th>
        <th>分值</th>
        <shiro:hasPermission name="pj:details:pjValueDetails:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="pjValueDetails">
        <tr>
            <td>
                <a href="${ctx}/pj/details/pjValueDetails/form?id=${pjValueDetails.id}">
                        ${pjValueDetails.name.length()<=80?pjValueDetails.name:pjValueDetails.name.substring(0,80)}
                </a>
            </td>
            <td>
                    ${pjValueDetails.pjValueCategory.name}
            </td>
            <td>
                    ${pjValueDetails.score}
            </td>
            <shiro:hasPermission name="pj:details:pjValueDetails:edit">
                <td>
                    <a href="${ctx}/pj/details/pjValueDetails/form?id=${pjValueDetails.id}">修改</a>
                    <a href="${ctx}/pj/details/pjValueDetails/delete?id=${pjValueDetails.id}"
                       onclick="return confirmx('确认要删除该评定细则吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>