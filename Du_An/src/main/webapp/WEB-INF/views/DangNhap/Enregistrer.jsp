<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Account User</title>
    <!--===============================================================================================-->
    <link rel="icon" type="/image/png" href="/images/icons/favicon.jpg"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/css/util.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <!--===============================================================================================-->

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">


</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt style="margin-bottom: 10px; width: 200px">
                <img src="/images/img-01.jpg" alt="IMG">
                <div style="margin-top: 50px; margin-left: 70px">
                    <a href="/home">
                        <button class=" btn btn-primary">Back</button>
                    </a>
                </div>
            </div>
            <p style="color: red">${er}</p>

            <frm:form cssStyle=" margin-left: 10px; width: 520px; margin-top: 4px"
                      modelAttribute="user"
                      action="${pageContext.request.contextPath}/addAccountUser"
                      class="login100-form validate-form"
                      method="post">

                <div class="row">
                    <h5 style="margin-bottom: 30px; margin-left: 100px; font-weight: bold">Đăng ký thông tin tài khoản người dùng</h5>
                    <div class="row">
                        <div class="col-6">
                            <label style="margin-left: 40px">Mã User</label>
                            <frm:input class="form-control" placeholder="Mã User" cssStyle="width: 250px; margin-left: 40px; margin-right: 30px"  path="maNV"/>
                            <p style="color: red">${erMa}</p>
                        </div>
                        <div class="col-6">
                            <label style="margin-left: 40px">Email</label>
                            <frm:input class="form-control" placeholder="Email" cssStyle="width: 250px; margin-left: 30px"  path="email"/>
                            <p style="color: red">${er}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <label style="margin-left: 40px; margin-top: 20px">Tên User</label>
                            <frm:input class="form-control" placeholder="Tên User" cssStyle="width: 250px; margin-left: 40px; margin-right: 30px"  path="tenUser"/>
                        </div>
                        <div class="col-6">
                            <label style="margin-left: 40px; margin-top: 20px">Mật khẩu</label>
                            <frm:input class="form-control" placeholder="Mật khẩu" cssStyle="width: 250px; margin-left: 30px"  path="password"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <label style="margin-left: 40px; margin-top: 20px; margin-bottom: 20px">Giới tính</label>
                            <br>
                            <frm:radiobutton path="gioiTinh" value="Nam" checked="checked" cssStyle="margin-left: 39px"/>Nam
                            <frm:radiobutton cssStyle="margin-left: 30px" path="gioiTinh" value="Nữ"/>Nữ


                        </div>
                        <div class="col-6">
                            <label style="margin-left: 70px; margin-top: 20px">Địa chỉ</label>
                            <frm:input class="form-control" placeholder="Địa chỉ" cssStyle="width: 250px; margin-left: 57px"  path="diaChi"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <label style="margin-left: 40px; margin-top: 20px">Ngày sinh</label>
                            <frm:input class="form-control" placeholder="Ngày sinh" cssStyle="width: 250px; margin-left: 40px; margin-right: 30px"  path="ngaySinh"/>
                        </div>
                        <div class="col-6">
                            <label style="margin-left: 40px; margin-top: 20px">Số điện thoại</label>
                            <frm:input class="form-control" placeholder="Số điện thoại" cssStyle="width: 250px; margin-left: 30px"  path="soDienThoai"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <label style="margin-left: 40px; margin-top: 20px">Role</label>
<%--                            <frm:input class="form-control" placeholder="Role" cssStyle="width: 250px; margin-left: 40px; margin-right: 30px"  path="role"/>--%>
                            <frm:select path="role" cssStyle="width: 200px; margin-left:40px; height: 35px; border-radius: 10px 10px 10px ">
                                <option value="ADMIN">
                                        Admin
                                </option>
                                <option value="MEMBER">
                                        Member
                                </option>
                            </frm:select>
                        </div>
                        <div class="col-6">
                            <label style="margin-left: 40px; margin-top: 20px">Trạng thái</label>
                            <frm:select path="trangThai" cssStyle="width: 200px; margin-left: 30px; height: 35px; border-radius: 10px 10px 10px ">
                                <option value="1">
                                    Đang làm việc
                                </option>
                                <option value="0">
                                    Đã nghỉ làm
                                </option>
                            </frm:select>
                        </div>
                    </div>
                </div>

                <div class="container-login100-form-btn" style="width: 150px; margin-top: 30px; margin-bottom: 30px; margin-left: 200px">
                    <button type="submit" class="login100-form-btn">
                        Login
                    </button>
                </div>
            </frm:form>

        </div>
    </div>
</div>




<!--===============================================================================================-->
<script src="/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="/vendor/bootstrap/js/popper.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="/vendor/tilt/tilt.jquery.min.js"></script>
<script >
    $('.js-tilt').tilt({
        scale: 1.1
    })
</script>
<!--===============================================================================================-->
<script src="/js/main.js"></script>



</body>
</html>