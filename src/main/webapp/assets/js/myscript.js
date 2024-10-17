function createComplain() {
    // Tạo một đối tượng XMLHttpRequest
    var xhr = new XMLHttpRequest();

    // Xác định phương thức và URL yêu cầu
    xhr.open("POST", "/api/complain/create", true);

    xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");

    var title = document.getElementById("title").value;
    var comment = document.getElementById("comment").value;

    var jsonBody = {
        title: title,
        comment: comment,
    };

    xhr.send(JSON.stringify(jsonBody));
    // Xử lý phản hồi từ máy chủ
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 201) {
                // Xử lý dữ liệu từ phản hồi ở đây
                var responseData = xhr.responseText;
                console.log(responseData);
                window.location.href = "/complain"
            } else {
                // Xử lý lỗi (nếu có)
                console.error("Requirement Error: " + xhr.status);
            }
        }
    };
}