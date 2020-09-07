<%--
  Created by IntelliJ IDEA.
  User: cx
  Date: 2020/9/7
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>留言板</title>
    <meta charset="UTF-8">
    <style>
        div {
            margin: 0;
            padding: 0;
            font-size: 12px;
            margin: 0 auto;
        }

        h3 {
            text-align: center
        }

        #container {
            width: 500px;
        }

        .article {
            border: 1px solid #a6cbe7;
            margin-top: 5px;
        }

        .author {
            background-color: #0099FF;
            width: 100%;
            height: 24px;
            line-height: 24px;
        }

        .content {
            height: 40px;
            padding: 10px;
        }

        .author span {
            float: right;
            padding-right: 10px;
        }

        .time {
            border-top: solid 1px #a6cbe7;
        }

        .page {
            text-align: right;
            height: 30px;
            line-height: 30px;
            padding-right: 10px;
        }
    </style>
</head>

<body>
<div id="container">
    <div><h3>留言板</h3></div>
    <div>
        <c:forEach items="${requestScope.messages}" var="message">
        <div class="article">
            <div class="author">用户:${message.author}<span>${message.id}#</span></div>
            <div class="content">${message.msg}</div>
            <div class="time page">发表于:${message.date}</div>
        </div>
        </c:forEach>



    </div>

    <div class="page">
        <a href="get?pageNum=${param.pageNum <= 1 ? 1 : param.pageNum - 1}">上一页</a>
        <a href="get?pageNum=${param.pageNum >= maxPage ? maxPage : param.pageNum + 1}">下一页</a>
    </div><br>
    <div>
        <form action="insert" method="post">
            <div>
                用户: <input type="text" name="author" value=""/>
            </div><br>
            <div>
                留言: <textarea name="message" rows="5" cols="72"></textarea>
            </div>
            <div align="center"><input type="reset" value="清除"/> <input type="submit" value="发表"/></div>
        </form>
    </div>
</div>
</body>
</html>

