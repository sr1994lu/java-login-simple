<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String strUserID = (String) session.getAttribute("userid");
    String strPasswd = (String) session.getAttribute("passwd");
    String strComment = (String) session.getAttribute("comment");
%>
<html>
<head>
    <title>Insert title here</title>
</head>
<body>
    【OK】認証完了画面
    <hr>
    ■入力値<br>ユーザID：<%= strUserID %><br>パスワード：<%= strPasswd %>
    <hr>
    ■コメント<br><%= strComment %>
    <hr>
</body>
</html>
