<%--
  Created by IntelliJ IDEA.
  User: nichijoux
  Date: 2022/8/9
  Time: 10:56
  To change this template use File | Settings | File Templates.
  用户角色分配页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="../include/head.jsp" %>
<script type="text/javascript">
    $(function () {
        // 左侧选择移到右侧列表
        $("#toRightBtn").click(function () {
            /*
            select 是标签选择器
            :eq(0)表示选择页面上的第一个
            :eq(1)表示选择页面上的第二个
            “>” 表示选择子元素
            :selected 表示选择“被选中的” option
            appendTo()能够将 jQuery 对象追加到指定的位置
            */
            $("select:eq(0)>option:selected").appendTo("select:eq(1)");
        });
        // 右侧选择移到左侧列表
        $("#toLeftBtn").click(function () {
            $("select:eq(1)>option:selected").appendTo("select:eq(0)");
        });
        // 提交角色
        $("#submitBtn").click(function () {
            $("select:eq(0)>option").prop("selected", "selected");
        });
    })
</script>


<body>
<%@include file="../include/navbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="../include/sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="admin/toMainPage">首页</a></li>
                <li><a href="admin/pageQueryAdmin">数据列表</a></li>
                <li class="active">更新</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="assign/assignRole" method="post" role="form" class="form-inline">
                        <input type="hidden" name="adminId" value="${param.adminId}">
                        <input type="hidden" name="pageNum" value="${param.pageNum}">
                        <input type="hidden" name="keyword" value="${param.keyword}">
                        <%--已分配角色--%>
                        <div class="form-group" style="width:100px;overflow-y:auto;">
                            <label>已分配角色列表</label><br>
                            <label>
                                <select name="roleIdList" class="form-control" multiple="multiple" size="10"
                                        style="width:100px;overflow-y:auto;">
                                    <c:forEach items="${requestScope.assignedRoleList}" var="role">
                                        <option value="${role.id}">${role.name}</option>
                                    </c:forEach>
                                </select>
                            </label>
                        </div>
                        <div class="form-group" style="margin-right: 40px;">
                            <ul>
                                <li id="toRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br/>
                                <li id="toLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left"
                                    style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <%--未分配的角色--%>
                        <div class="form-group">
                            <label>未分配角色列表</label><br>
                            <label>
                                <select class="form-control" multiple="" size="10" style="width:100px;overflow-y:auto;">
                                    <c:forEach items="${requestScope.unAssignedRoleList}" var="role">
                                        <option value="${role.id}">${role.name}</option>
                                    </c:forEach>
                                </select>
                            </label>
                        </div>

                        <button id="submitBtn" type="submit" style="width: 100px;margin-left: 100px;"
                                class="btn btn-lg btn-success btn-block">提交
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
