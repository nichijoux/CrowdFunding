<%--
  Created by IntelliJ IDEA.
  User: nichijoux
  Date: 2022/8/8
  Time: 18:02
  To change this template use File | Settings | File Templates.
  统一错误页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".btn").click(function () {
                // 相当于浏览器的后退按钮
                window.history.back();
            });
        });
    </script>
    <style>

    </style>
    <title>Crowd错误消息</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">Crowd-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">
    <h2 class="form-signin-heading" style="text-align: center;"><i class="glyphicon glyphicon-log-in"></i>Crowd系统消息</h2>
    <!--
        requestScope对应的是存放request域数据的Map
        requestScope.exception相当于request.getAttribute("exception")
        requestScope.exception.message相当于exception.getMessage()
    -->
    <h3 style="text-align: center;"> ${ requestScope.exception.message } </h3>
    <button style="width: 150px;margin: 50px auto 0 auto;" type="submit" class="btn btn-lg btn-success btn-block">
        返回上一步
    </button>

</div>
</body>
</html>
