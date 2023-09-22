<%--
  Created by IntelliJ IDEA.
  User: defaultuser0
  Date: 9/18/2023
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../../common/taglib.jsp" %>
<html>
<head>
    <title>Create Complain</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h2>Add New Complain</h2>
<form>
    <fieldset>
        <legend>
            Complain Form
        </legend>
        <table class="table table-striped">
            <tr>
                <th scope="row"><label for="title">Title:</label></th>
                <td><input type="text" id="title" name="title" size="45" title="letter and number allowed"></td>
            </tr>
            <tr>
                <th scope="row"><label for="comment">Comment:</label></th>
                <td><input type="text" id="comment" name="comment" size="45" title="letter and number allowed"></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <a href="/complain">
                        <button class="btn btn-success" type="button">Cancel</button>
                    </a>
                    <button type="button" onclick="createComplain()" class="btn btn-primary">Create</button>
                </td>
            </tr>
        </table>
    </fieldset>
</form>
<script>
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
</script>
</body>
</html>
