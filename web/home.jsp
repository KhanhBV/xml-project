<%-- 
    Document   : home
    Created on : Jul 18, 2020, 10:00:43 AM
    Author     : vankhanhbui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/HomeStyle.css">
        <title>Home</title>
    </head>
    <body>
        <div class="container">
            <c:set var="result" value="${sessionScope.LISTALLPRODUCT}"/>
            <h2>
                <a href="DispatcherServlet">Home Product</a>
            </h2>
            <form action="DispatcherServlet">
                <div class="box-search">
                    <select class="select-brand" value="${LISTCATEGORY}" name="nameCategory">
                        <option value="0">-- Category --</option>
                        <c:forEach var="cate" items="${LISTCATEGORY}">
                            <option value="${cate.name}">${cate.name}</option>
                        </c:forEach>
                    </select>         

                    <input type="submit" class="buttonsearch" name="btAction" value="Search">
                </div>
                <div class="box-search">
                    <select class="select-brand" value="${LISTBRAND}" name="nameBrand">
                        <option value="0">-- Brand --</option>
                        <c:forEach var="brand" items="${LISTBRAND}">
                            <option value="${brand.name}">${brand.name}</option>
                        </c:forEach>
                    </select>     
                </div>
                <div class="box-search">
                    <input type="text" name="txtNameSearch" value="${param.txtNameSearch}" />
                </div>
            </form>
            <form action="DispatcherServlet">
                <input type="submit" class="button-add-product" name="btAction" value="Add New Product"/>
            </form>
            <c:if test="${not empty result}">
                <table>
                    <thead>
                    <th>STT</th>
                    <th>Product Name</th>
                    <th>Power (kW)</th>
                    <th>Action</th>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatcherServlet">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.name}
                                </td>
                                <td>${dto.power}</td>
                                <td>
                                    <%--                            <input type="submit" class="buttonadd" value="Add" name="btAction">--%>
                                    <%--                            <input type="hidden" name="nameCategory" value="${param.nameCategory}"/>--%>
                                    <c:url value="DispatcherServlet" var="URLAdd">
                                        <c:param name="btAction" value="Add"/>
                                        <c:param name="idProduct" value="${dto.id}"/>
                                        <c:param name="nameCategory" value="${param.nameCategory}"/>
                                        <c:param name="nameBrand" value="${param.nameBrand}"/>
                                        <c:param name="txtNameSearch" value="${param.txtNameSearch}"/>

                                    </c:url>
                                    <a href="${URLAdd}">Add</a>
                                </td>
                            </tr>
                            <tr>
                            </tr>
                        </form>
                    </c:forEach>

                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty result}">
                <h3>No records</h3>
            </c:if>

            <br/>

        </div>
        <br/>
        <br/>
        <br/>
        <br/>
        <div class="container">
            <c:set var="list" value="${sessionScope.CART}"/>
            <c:if test="${not empty list}">
                <form action="DispatcherServlet">
                    <table>
                        <thead>
                        <th>STT</th>
                        <th>Electric Product Name</th>
                        <th>Electric Product Power (kW)</th>
                        <th>Quantity</th>
                        <th>Using Time( Hour / Day)</th>
                        <th>Action</th>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${list.items}" varStatus="counter">

                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${dto.value.name}
                                        <input type="hidden" name="idProduct" value="${dto.value.id}"/>
                                    </td>
                                    <td>${dto.value.power}
                                        <input type="hidden" name="capacityProduct" value="${dto.value.power}">
                                    </td>
                                    <td>
                                        <input type="text" name="txtQuantity" value="" required>
                                    </td>
                                    <td>
                                        <input type="text" name="txtTime" value="" required>
                                    </td>
                                    <td>
                                        <c:url value="DispatcherServlet" var="localURL">
                                            <c:param name="btAction" value="Remove"/>
                                            <c:param name="idProduct" value="${dto.key}"/>
                                        </c:url>
                                        <a href="${localURL}">Remove</a>
                                    </td>
                                </tr>

                            </c:forEach>
                        </tbody>
                    </table>
                    <c:set var="money" value="${requestScope.MONEY}"/>
                    <c:set var="capa" value="${requestScope.CAPA}"/>
                    <br/>
                    <input type="Submit" class="button-caculate" value="Caculate Electric Money" name="btAction">
                    
                    <input type="submit" class="button-find-pin" value="Find Generator" name="btAction" />
                    <c:if test="${not empty capa}">
                        <br>
                        <br>
                        <h3>Used Power 1 month: ${capa} kW</h3>
                        <input type="hidden" name="totalCapacity" value="${capa}">
                    </c:if>
                    <c:if test="${not empty money}">
                        <h3>Your electric money (1 month) is : ${money} VND</h3>
                        <input type="hidden" name="totalMoney" value="${money}">
                    </c:if>
                    <c:if test="${empty money}">
                        <h4>No money</h4>
                    </c:if>
                </form>
                <br/>
            </c:if>
        </div>
        <div>
            <form action="DispatcherServlet">
                <input type="submit" value="Crawl Data" name="btAction" />
            </form>
        </div>

    </body>
</html>
