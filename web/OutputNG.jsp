<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String strUserID = (String) request.getAttribute("userid");
    String strPasswd = (String) request.getAttribute("passwd");
    String strComment = (String) request.getAttribute("comment");
%>
<html>
<head>
    <title>Insert title here</title>
</head>
<body>
    【NG】エラー画面
    <hr>
    ■入力値<br>ユーザID：<%= strUserID %><br>パスワード：<%= strPasswd %>
    <hr>
    ■コメント<br><%= strComment %>
    <hr>
    <a href="input.html">戻る</a>
    <hr>
</body>
</html>
