//Show form
$(document).ready(function () {
    $('#showFormAdd').click(function () {
        $('#MaterialModal').modal('show');
    });
});

//Add and update color
function saveMaterial() {
    // Lấy dữ liệu từ biểu mẫu
    var materialName = $("#materialName").val().trim();
    var materialDescription = $("#materialDescription").val().trim();
    var currentTime = moment().format('YYYY-MM-DD');
    var materialId = $("#materialForm").attr("material-id-update");

    // Kiểm tra xem các trường có rỗng không
    if (materialName === "" || materialDescription === "") {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Vui lòng điền đầy đủ thông tin!'
        });
        return;
    }

    var nameRegex = /^[a-zA-ZÀ-ỹ\s]+$/;
    if (!nameRegex.test(materialName)) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Tên chỉ được chứa chữ cái và khoảng trắng!'
        });
        return;
    }

    var url = materialId ? "/admin/materials/update/" + materialId : "/admin/materials/add";
    var method = materialId ? "PUT" : "POST";
    var dataToSend = {
        name: materialName,
        description: materialDescription,
    }
    if (materialId) {
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
function updateMaterialForm(element) {
    var materialId = element.getAttribute("data-material-id");
    // Thêm thuộc tính để kiểm tra xem add hay update
    $('#materialForm').attr('material-id-update', materialId);
    // Thực hiện AJAX request để lấy dữ liệu danh mục từ backend
    $.ajax({
        type: 'GET',
        url: '/admin/materials/formUpdate/' + materialId,
        success: function (material) {
            // Điền dữ liệu vào các trường biểu mẫu
            $('#materialName').val(material.name);
            $('#materialDescription').val(material.description);

            // Hiển thị hộp thoại modal
            $('#MaterialModal').modal('show');

            // Lắng nghe sự kiện đóng modal
            $('#MaterialModal').on('hidden.bs.modal', function () {
                // Xóa thuộc tính category-id-update khi modal đóng
                $('#materialForm').removeAttr('material-id-update');
            });
        },
        error: function (error) {
            console.log('Error fetching material data:', error);
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
        url: "/admin/materials/search",
        contentType: "application/json",
        data: {
            name: searchInput
        },
        success: function (response) {
            console.log(response);
            window.location.href = '/admin/materials/search?name=' + encodeURIComponent(searchInput);
        },
        error: function (error) {
            console.log(error)
        }
    });
}

//Set Status
function toggleStatus(checkbox) {
    var categoryId = checkbox.getAttribute("data-material-id");
    // Gửi yêu cầu AJAX để cập nhật trạng thái của danh mục
    //Sử dụng jQuery
    $.ajax({
        type: "POST",
        url: "/admin/materials/setStatus/" + categoryId,
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