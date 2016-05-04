<%--
  Created by IntelliJ IDEA.
  User: OmeN
  Date: 02.05.2016
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Test title</title>
  </head>
  <body>
    <h1><%= request.getAttribute("session_parameter")%></h1><br>
    <a href="/reg.jsp">Registration</a><br>
    <a href="ServletListUsers">List users</a><br>
    <a href="/ServletHibernetList">List hibernet</a><br>
  </body>
</html>
