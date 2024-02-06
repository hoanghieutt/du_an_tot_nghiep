//Show form
$(document).ready(function () {
    $('#showFormAdd').click(function () {
        $('#CategoryModal').modal('show');
    });
});

//Add and update category
function saveCategory() {
    // Lấy dữ liệu từ biểu mẫu
    var categoryName = $("#categoryName").val().trim();
    var categoryDescription = $("#categoryDescription").val().trim();
    var currentTime = moment().format('YYYY-MM-DD');
    var categoryId = $("#categoryForm").attr("category-id-update");

    // Kiểm tra xem các trường có rỗng không
    if (categoryName === "" || categoryDescription === "") {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Vui lòng điền đầy đủ thông tin danh mục!'
        });
        return;
    }

    var nameRegex = /^[a-zA-ZÀ-ỹ\s]+$/;
    if (!nameRegex.test(categoryName)) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Tên danh mục chỉ được chứa chữ cái và khoảng trắng!'
        });
        return;
    }

    var url = categoryId ? "/admin/category/update/" + categoryId : "/admin/category/add";

    var method = categoryId ? "PUT" : "POST";

    var dataToSend = {
        name: categoryName,
        description: categoryDescription,
    }

    if (categoryId) {
        dataToSend.id = categoryId;
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
            console.log("Lưu danh mục thành công!");

            Swal.fire({
                icon: 'success',
                title: 'Thành công!',
                text: 'Lưu danh mục thành công!',
                didClose: function () {
                    location.reload();
                }
            });
        },
        error: function (error) {
            console.error("Lỗi khi lưu danh mục:", error);

            Swal.fire({
                icon: 'error',
                title: 'Lỗi!',
                text: 'Có lỗi xảy ra khi lưu danh mục!'
            });
        }
    });
}

// Hàm để cập nhật biểu mẫu với dữ liệu danh mục
function updateCategoryForm(element) {
    var categoryId = element.getAttribute("data-category-id");
    // Thêm thuộc tính để kiểm tra xem add hay update
    $('#categoryForm').attr('category-id-update', categoryId);
    // Thực hiện AJAX request để lấy dữ liệu danh mục từ backend
    $.ajax({
        type: 'GET',
        url: '/admin/category/formUpdate/' + categoryId,
        success: function (category) {
            // Điền dữ liệu vào các trường biểu mẫu
            $('#categoryName').val(category.name);
            $('#categoryDescription').val(category.description);

            // Hiển thị hộp thoại modal
            $('#CategoryModal').modal('show');

            // Lắng nghe sự kiện đóng modal
            $('#CategoryModal').on('hidden.bs.modal', function () {
                // Xóa thuộc tính category-id-update khi modal đóng
                $('#categoryForm').removeAttr('category-id-update');
            });
        },
        error: function (error) {
            console.log('Error fetching category data:', error);
            // Xử lý lỗi nếu cần
        }
    });
}

//Set Status
function toggleStatus(checkbox) {
    var categoryId = checkbox.getAttribute("data-category-id");
    // Gửi yêu cầu AJAX để cập nhật trạng thái của danh mục
    //Sử dụng jQuery
    $.ajax({
        type: "POST",
        url: "/admin/category/setStatus/" + categoryId,
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

// Search All
function searchAll() {
    var searchInput = $("#searchInput").val().trim();
    // Gửi yêu cầu AJAX
    $.ajax({
        type: 'GET',
        url: "/admin/category/searchAll",
        contentType: "application/json",
        data: {
            name: searchInput
        },
        success: function (response) {
            console.log(response);
            window.location.href = '/admin/category/searchAll?name=' + encodeURIComponent(searchInput);
        },
        error: function (error) {
            console.log(error)
        }
    });
}