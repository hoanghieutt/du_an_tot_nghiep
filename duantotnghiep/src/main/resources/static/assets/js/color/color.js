//Show form
$(document).ready(function () {
    $('#showFormAdd').click(function () {
        $('#ColorModal').modal('show');
    });
});

//Add and update color
function saveColor() {
    // Lấy dữ liệu từ biểu mẫu
    var colorName = $("#colorName").val().trim();
    var colorDescription = $("#colorDescription").val().trim();
    var currentTime = moment().format('YYYY-MM-DD');
    var colorId = $("#colorForm").attr("color-id-update");

    // Kiểm tra xem các trường có rỗng không
    if (colorName === "" || colorDescription === "") {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Vui lòng điền đầy đủ thông tin!'
        });
        return;
    }

    var nameRegex = /^[a-zA-ZÀ-ỹ\s]+$/;
    if (!nameRegex.test(colorName)) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Tên chỉ được chứa chữ cái và khoảng trắng!'
        });
        return;
    }

    var url = colorId ? "/admin/colors/update/" + colorId : "/admin/colors/add";
    var method = colorId ? "PUT" : "POST";
    var dataToSend = {
        name: colorName,
        description: colorDescription,
    }
    if (colorId) {
        dataToSend.id = colorId;
        dataToSend.updatedDate = currentTime;
    } else {
        dataToSend.createdDate = currentTime;
    }

    // Gửi yêu cầu AJAX
    $.ajax({
        type: method,
        url: url,
        contentType: "application/json",
        data: JSON.stringify(dataToSend),
        success: function (response) {
            console.log("Lưu thành công!");

            Swal.fire({
                icon: 'success',
                title: 'Thành công!',
                text: 'Lưu thành công!',
                didClose: function () {
                    location.reload();
                }
            });
        },
        error: function (error) {
            console.error("Lỗi khi lưu:", error);

            Swal.fire({
                icon: 'error',
                title: 'Lỗi!',
                text: 'Có lỗi xảy ra khi lưu!'
            });
        }
    });
}

// Hàm để cập nhật biểu mẫu với dữ liệu danh mục
function updateColorForm(element) {
    var categoryId = element.getAttribute("data-color-id");
    // Thêm thuộc tính để kiểm tra xem add hay update
    $('#colorForm').attr('color-id-update', categoryId);
    // Thực hiện AJAX request để lấy dữ liệu danh mục từ backend
    $.ajax({
        type: 'GET',
        url: '/admin/colors/formUpdate/' + categoryId,
        success: function (category) {
            // Điền dữ liệu vào các trường biểu mẫu
            $('#colorName').val(category.name);
            $('#colorDescription').val(category.description);

            // Hiển thị hộp thoại modal
            $('#ColorModal').modal('show');

            // Lắng nghe sự kiện đóng modal
            $('#ColorModal').on('hidden.bs.modal', function () {
                // Xóa thuộc tính category-id-update khi modal đóng
                $('#colorForm').removeAttr('color-id-update');
            });
        },
        error: function (error) {
            console.log('Error fetching category data:', error);
            // Xử lý lỗi nếu cần
        }
    });
}

// Search All
function searchAll() {
    var searchInput = $("#searchInput").val().trim();
    // Gửi yêu cầu AJAX
    $.ajax({
        type: 'GET',
        url: "/admin/colors/search",
        contentType: "application/json",
        data: {
            name: searchInput
        },
        success: function (response) {
            console.log(response);
            window.location.href = '/admin/colors/search?name=' + encodeURIComponent(searchInput);
        },
        error: function (error) {
            console.log(error)
        }
    });
}

//Set Status
function toggleStatus(checkbox) {
    var categoryId = checkbox.getAttribute("data-color-id");
    // Gửi yêu cầu AJAX để cập nhật trạng thái của danh mục
    //Sử dụng jQuery
    $.ajax({
        type: "POST",
        url: "/admin/colors/setStatus/" + categoryId,
        success: function (response) {
            // Xử lý thành công, nếu cần
            console.log("Cập nhật trạng thái thành công");

            // Hiển thị thông báo thành công sử dụng SweetAlert2
            Swal.fire({
                icon: 'success',
                title: 'Thành công!',
                text: 'Trạng thái đã được cập nhật thành công!'
            });
        },
        error: function (error) {
            // Xử lý lỗi, nếu cần
            console.error("Lỗi khi cập nhật trạng thái");

            // Hiển thị thông báo thất bại sử dụng SweetAlert2
            Swal.fire({
                icon: 'error',
                title: 'Lỗi!',
                text: 'Có lỗi xảy ra khi cập nhật trạng thái.'
            });
        }
    });
}