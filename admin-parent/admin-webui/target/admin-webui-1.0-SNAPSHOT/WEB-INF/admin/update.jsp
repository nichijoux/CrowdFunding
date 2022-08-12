<%--
  Created by IntelliJ IDEA.
  User: nichijoux
  Date: 2022/8/9
  Time: 8:50
  To change this template use File | Settings | File Templates.
  admin更新信息页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="../include/head.jsp" %>

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
                <div class="panel-heading">表单数据
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <form action="admin/updateAdmin" method="post" role="form">
                        <input type="hidden" name="id" value="${requestScope.admin.id}">
                        <input type="hidden" name="pageNum" value="${param.pageNum}">
                        <input type="hidden" name="keyword" value="${param.keyword}">
                        <p>${requestScope.exception.message}</p>
                        <div class="form-group">
                            <label for="InputUsername">用户昵称</label>
                            <input type="text" name="username" value="${requestScope.admin.username}"
                                   class="form-control" id="InputUsername" placeholder="请输入用户名称">
                        </div>
                        <div class="form-group">
                            <label for="InputPassword">登录密码</label>
                            <input type="text" name="password" value="${requestScope.admin.password}"
                                   class="form-control" id="InputPassword" placeholder="请输入登录密码">
                        </div>
                        <div class="form-group">
                            <label for="InputEmail">邮箱地址</label>
                            <input type="email" name="email" value="${requestScope.admin.email}" class="form-control"
                                   id="InputEmail" placeholder="请输入邮箱地址">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 更新
                        </button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>