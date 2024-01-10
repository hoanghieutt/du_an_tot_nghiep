<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<form action="/loai-san-pham/update?id=${loaiSanPhams.id}" method="post">

    Mã: <input type="text" name="ma" value="${loaiSanPhams.maCL}"/>

    <br/>
    Tên: <input type="text" name="ten" value="${loaiSanPhams.ten}"/>
    <br/>

    Ngày Tạo: <input type="date" name="ngayTao" value="${loaiSanPhams.ngayTao}"/>

    <br/>
    Ngày Sửa: <input type="date" name="ngaySua" value="${loaiSanPhams.ngaySua}"/>
    <br/>

    <button type="submit">Upadte</button>

</form>


 </body>
</html>
