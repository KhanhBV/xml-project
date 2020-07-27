<%-- 
    Document   : generator
    Created on : Jul 25, 2020, 9:37:11 AM
    Author     : vankhanhbui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/HomeStyle.css">
        <title>Generator</title>
    </head>
    <body>
        <h2>Generator Page</h2>
        <c:set var="result" value="${sessionScope.LISTGENERATORBYPOWER}"/>

        <c:if test="${not empty result}">
            <div class="container-pin">
                <h2>
                    <a href="HomeProductServlet">Electric Product</a>
                </h2><br>
                
                <h2>Generator List</h2><br>
                <h3 style="color: red">
                    Total power of electric Product: ${sessionScope.TOTALPOWER}
                </h3>
                <div class="row">
                    <c:forEach var="dto" items="${result}">
                        <div class="column">

                            <div class="card">
                                <img class="lazy" src="${dto.imageURL}" width="200px"
                                     height="200px">
                                <h3>${dto.name}
                                </h3>

                                <h4>Power:<font color="red" size="3"> ${dto.power} kW</font>
                                </h4>
                                <h4>
                                    Link generator: <a href="${dto.url}">Click here to access link</a>
                                </h4>
                            </div>

                        </div>
                    </c:forEach>
                </div>

            </div>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                No generator suitable were found!!<br>
                <a style="color: red" href="home.jsp">Click here to try again...</a>
            </h2>
        </c:if>
    </body>
</html>
