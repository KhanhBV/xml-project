<%-- 
    Document   : addNewProduct
    Created on : Jul 21, 2020, 1:00:19 AM
    Author     : vankhanhbui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/HomeStyle.css">
        <title>Add New Product</title>
    </head>
    <body>
        <div class="container-addnew">
            <form action="DispatcherServlet">

                <h2>Add new Product</h2>

                <label><b>Product Name (*)</b></label>
                <input type="text" name="productName" placeholder="Name Product" required></br>
                <label><b>Power kW (*)</b></label>
                <input type="text" name="capacityProduct" placeholder="Power" required></br>
                <label><b>Image URL</b></label>
                <input type="text" name="imageURL" placeholder="Image URL" ></br>
                <label><b>URL Product</b></label>
                <input type="text" name="urlProduct" placeholder="URL Product"></br>
                <label><b>Brand Name (*)</b></label>
                <select value="${LISTBRAND}" name="brandProduct" required>
                    <option value="">-- Brand --</option>
                    <c:forEach var="brand" items="${LISTBRAND}">
                        <option value="${brand.name}">${brand.name}</option>
                    </c:forEach>
                </select>
                <br/>
                <br/>
                <label><b>Category Name(*)</b></label>
                <select value="${LISTCATEGORY}" name="nameCategory" required>
                    <option value="">-- Category --</option>
                    <c:forEach var="cate" items="${LISTCATEGORY}">
                        <option value="${cate.name}">${cate.name}</option>
                    </c:forEach>
                </select>
                <br/>
                <br/>
                <input type="submit" class="addnewbtn" name="btAction" value="Add New">

            </form>
        </div>
    </body>
</html>
