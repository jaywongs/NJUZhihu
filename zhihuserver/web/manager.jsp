<%--
  Created by IntelliJ IDEA.
  User: stephencurry
  Date: 2018/12/24
  Time: 下午11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>manager</title>
    <style type="text/css">
        /* gridtable */
        table.gridtable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }
        table.gridtable th {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<h2>单人问答</h2>
<table class="gridtable">
    <thead>
    <tr>
        <td>问题名</td>
        <td>状态</td>
        <td>选项</td>
    </tr>
    </thead>
    <c:forEach items="${questions}" var="q">
        <tr>
            <td width="50%">${q.content}</td>
            <td>${q.is_free}</td>
            <td><a href="/closeQuestions?question.id=${q.id}">切换</a></td>
        </tr>
    </c:forEach>

</table>
<h2>社区问答</h2>
<table class="gridtable">
    <thead>
    <tr>
        <td>问题名</td>
        <td>状态</td>
        <td>选项</td>
    </tr>
    </thead>
    <c:forEach items="${stories}" var="s">
        <tr>
            <td width="50%">${s.content}</td>
            <td>${s.is_free}</td>
            <td><a href="/closeStory?story.id=${s.id}">切换</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
