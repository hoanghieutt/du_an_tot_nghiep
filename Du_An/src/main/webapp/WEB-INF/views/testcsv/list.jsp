<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

                <h1>Test jsp</h1>

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col" style="text-align: center">#</th>
                        <th scope="col" style="text-align: center">Image</th>
                        <th scope="col" style="text-align: center">Mã sản phẩm</th>
                        <th scope="col" style="text-align: center">Tên sản phẩm</th>
                        <th scope="col" style="text-align: center">Price</th>
                        <th scope="col" style="text-align: center">Số lượng</th>
                        <th scope="col" style="text-align: center">Title</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="sp" items="${views}" varStatus="i">
                        <tr>
                            <th style="padding-top: 40px" scope="row">${i.index+1}</th>
                            <td class="imag"><img  width="100px" style="margin-top: 7px; margin-left: 50px" src="${pageContext.request.contextPath}/upload/${sp.image}"></td>
                            <td style="padding-top: 55px; text-align: center">${sp.maCSV}</td>
                            <td style="padding-top: 55px; text-align: center">${sp.name}</td>
                            <td style="padding-top: 55px ;text-align: center">${sp.price}</td>
                            <td style="padding-top: 55px; text-align: center">${sp.soLuong}</td>
                            <td style="padding-top: 55px; text-align: center">${sp.title}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

</body>
</html>