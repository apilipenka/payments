<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
    <script src="<c:url value="/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/js/bootstrap.min.js" />"></script>
</head>

<body>
<div class="container">
    <h2>Commands</h2>
    <!--Search Form -->
    <form action="controller" method="get" id="seachCommandForm" role="form">
        <input type="hidden" id="command" name="command" value="COMMANDLIST">
        <div class="form-group col-xs-5">
            <input type="text" name="commandName" id="commandName" class="form-control"
                   placeholder="Type the Name of the command"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>
        <br></br>
        <br></br>
    </form>

    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form action="controller" method="post" id="commandForm" role="form">
        <input type="hidden" id="commandID" name="commandID">
        <input type="hidden" id="command" name="command">
        <c:choose>
            <c:when test="${not empty commandList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td>Command</td>
                        <td>URL</td>
                        <td>Label</td>
                        <td>Comment</td>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="commando1" items="${commandList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${commandID ==commando1.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="controller?commandID=${commando1.id}&command=EDITCOMMAND">${commando1.id}</a>
                            </td>
                            <td>${commando1.command}</td>
                            <td>${commando1.url}</td>
                            <td>${commando1.label}</td>
                            <td>${commando1.comment}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=REMOVECOMMAND&commandID=${commando1.id}"
                                   id="remove">
                                    <span class="glyphicon glyphicon-trash"/>
                                </a>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    No commnd found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>

    <form action="${pageContext.request.contextPath}/jsp/new-command.jsp">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md">New command</button>
    </form>
</div>
</body>
</html>