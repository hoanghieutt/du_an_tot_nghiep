//Show form
$(document).ready(function () {
    $('#showFormAdd').click(function () {
        $('#SizeModal').modal('show');
    });
});

//Add and update color
function saveSize() {
    // Lấy dữ liệu từ biểu mẫu
    var sizeName = $("sizeName").val().trim();
    var sizeShirtLength = $("#sizeShirtLength").val().trim();
    var sizeShirtWidth = $("#sizeShirtWidth").val().trim();
    var currentTime = moment().format('YYYY-MM-DD');
    var sizeId = $("#sizeForm").attr("size-id-update");

    // Kiểm tra xem các trường có rỗng không
    if (sizeName === "" || sizeShirtWidth === "" || sizeShirtLength === "") {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Vui lòng điền đầy đủ thông tin!'
        });
        return;
    }

    var nameRegex = /^[a-zA-ZÀ-ỹ\s]+$/;
    if (!nameRegex.test(sizeName)) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Tên chỉ được chứa chữ cái và khoảng trắng!'
        });
        return;
    }

    var url = sizeId ? "/admin/sizes/update/" + sizeId : "/admin/sizes/add";
    var method = sizeId ? "PUT" : "POST";
    var dataToSend = {
        name: sizeName,
        shirtWidth: sizeShirtWidth,
        shirtLength: sizeShirtLength
    }
    if (sizeId) {
        dataToSend.id = materialId;
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
function updateSizeForm(element) {
    var sizeId = element.getAttribute("data-size-id");
    // Thêm thuộc tính để kiểm tra xem add hay update
    $('#sizeForm').attr('size-id-update',sizeId);
    // Thực hiện AJAX request để lấy dữ liệu danh mục từ backend
    $.ajax({
        type: 'GET',
        url: '/admin/sizes/formUpdate/' + sizeId,
        success: function (size) {
            // Điền dữ liệu vào các trường biểu mẫu
            $('#sizeName').val(size.name);
            $('#sizeShirtLength').val(size.shirtLength);
            $('#sizeShirtWidth').val(size.shirtWidth);

            // Hiển thị hộp thoại modal
            $('#SizeModal').modal('show');

            // Lắng nghe sự kiện đóng modal
            $('#SizeModal').on('hidden.bs.modal', function () {
                // Xóa thuộc tính category-id-update khi modal đóng
                $('#sizeForm').removeAttr('size-id-update');
            });
        },
        error: function (error) {
            console.log('Error fetching size data:', error);
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
        url: "/admin/sizes/search",
        contentType: "application/json",
        data: {
            name: searchInput
        },
        success: function (response) {
            console.log(response);
            window.location.href = '/admin/sizes/search?name=' + encodeURIComponent(searchInput);
        },
        error: function (error) {
            console.log(error)
        }
    });
}

//Set Status
function toggleStatus(checkbox) {
    var sizeId = checkbox.getAttribute("data-size-id");
    // Gửi yêu cầu AJAX để cập nhật trạng thái của danh mục
    //Sử dụng jQuery
    $.ajax({
        type: "POST",
        url: "/admin/sizes/setStatus/" + sizeId,
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