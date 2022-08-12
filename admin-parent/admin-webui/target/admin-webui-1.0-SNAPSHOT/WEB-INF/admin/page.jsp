<%--
  Created by IntelliJ IDEA.
  User: nichijoux
  Date: 2022/8/8
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%--头引用--%>
<%@include file="../include/head.jsp" %>

<link rel="stylesheet" href="css/pagination.css">
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript">
    $(function () {
        // 调用后面声明的函数对页码导航条进行初始化操作
        initPagination();
    });

    function initPagination() {
        // 获取总记录数
        let totalRecord = ${requestScope.pageInfo.total};
        // 声明一个JSON对象存储Pagination要设置的属性
        let properties = {
            num_edge_entries: 3, // 边缘页数
            num_display_entries: 5, // 主体页数
            callback: pageSelectCallback,
            items_per_page:${requestScope.pageInfo.pageSize}, // 每页显示1项
            current_page: ${requestScope.pageInfo.pageNum - 1}, // Pagination内部使用pageIndex来管理页码，从0开始，而pageNum从1开始
            prev_text: "上一页",
            next_text: "下一页"
        };
        // 生成页码导航条
        $("#Pagination").pagination(totalRecord, properties);
    }

    // 用户点击页码时调用该函数实现跳转
    function pageSelectCallback(pageIndex, jQuery) {
        // 根据pageIndex计算得到pageNum
        let pageNum = pageIndex + 1;
        // 跳转页码
        window.location.href = "admin/pageQueryAdmin?pageNum=" + pageNum + "&keyword=${param.keyword}";
        // 由于每一个页码按钮都是超链接，所以在这个函数最后取消超链接的默认行为
        return false;
    }
</script>

<body>
<%@include file="../include/navbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="../include/sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form action="admin/pageQueryAdmin" method="post" class="form-inline" role="form"
                          style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <label for="InputKeyword"></label>
                                <input id="InputKeyword" name="keyword"
                                       class="form-control has-success" type="text"
                                       value="${param.keyword}"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='admin/toAddPage'"><i class="glyphicon glyphicon-plus"></i>
                        新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30">
                                    <label>
                                        <input type="checkbox">
                                    </label>
                                </th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty requestScope.pageInfo.list}">
                                <tr>
                                    <td colspan="6" align="center">抱歉！没有查询到你要的数据</td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty requestScope.pageInfo.list}">
                                <c:forEach items="${requestScope.pageInfo.list}" var="admin" varStatus="myStatus">
                                    <tr>
                                        <td>${myStatus.count}</td>
                                        <td>
                                            <label>
                                                <input type="checkbox">
                                            </label>
                                        </td>
                                        <td>${admin.account}</td>
                                        <td>${admin.username}</td>
                                        <td>${admin.email}</td>
                                        <td>
                                            <a href="assign/toAssignPage?adminId=${admin.id}&pageNum=${requestScope.pageInfo.pageNum}&keyword=${param.keyword}"
                                               class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></a>
                                            <a href="admin/toUpdatePage?adminId=${admin.id}&pageNum=${requestScope.pageInfo.pageNum}&keyword=${param.keyword}"
                                               class="btn btn-primary btn-xs"><i
                                                    class=" glyphicon glyphicon-pencil"></i></a>
                                            <a href="admin/deleteAdmin?adminId=${admin.id}&pageNum=${requestScope.pageInfo.pageNum}&keyword=${param.keyword}"
                                               class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-remove"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                            <%--分页条开始--%>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>
                            </tfoot>
                            <%--分页条结束--%>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

