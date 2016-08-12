<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome admin</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
    <script src="<c:url value="/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">
    <h3>Welcome, admin</h3>
    <hr/>
    ${user}, hello!
    <hr/>
    Links for admin...<br/>
    Debug info - session = ${sessionScope}
    <br></br>
    <ul>
        <li class="dropdown" style="list-style: none;">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a href="${pageContext.request.contextPath}/controller?command=USERROLELIST">Edit user roles</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/controller?command=USERLIST">Edit users</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=COMMANDLIST">Edit commands</a></li>
                <li class="divider"></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=BANKLIST">Edit banks</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=CURRENCYLIST">Edit currencies</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/controller?command=EXCHANGERATELIST">Edit exchange
                    rates</a></li>
                <li class="divider"></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a></li>
            </ul>
        </li>
    </ul>


</div>
</body>
</html>