<%--
  Created by IntelliJ IDEA.
  User: defaultuser0
  Date: 9/18/2023
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Detail Complain</title>
    <style>
        h2 {
            text-align: center;
            text-decoration: underline;
            font-family: "Roboto Light", sans-serif;
            color: brown;
        }
    </style>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<h2>Complain Information</h2>
<fieldset>
    <legend>
        Complain Information <c:out value="${complain.id}"/>
    </legend>
    <table>
        <tr>
            <th>Title: </th>
            <td><c:out value="${complain.title}"/></td>
        </tr>
        <tr>
            <th>Comment: </th>
            <td><c:out value="${complain.comment}"/></td>
        </tr>
        <tr>
            <th>Time Creation: </th>
            <td><c:out value="${complain.timeCreation}"/></td>
        </tr>
        <tr>
            <td>
                <a href="/complain"><button type="submit">Cancel</button></a>
            </td>
            <td>
                <a href="/complain/edit/${complain.id}">
                    <button type="submit">Edit</button>
                </a>
                <button type="button" onclick="deleteComplain(${complain.id})">Delete</button>
            </td>
        </tr>
    </table>
</fieldset>
</body>
<script type="text/javascript">
    function deleteComplain(id){
        const url = 'delete/' + id;
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = url;
                Swal.fire(
                    'Deleted!',
                    'Your file has been deleted.',
                    'success'
                );
            } else if (
                /* Read more about handling dismissals below */
                result.dismiss === Swal.DismissReason.cancel
            ) {
                Swal.fire(
                    'Cancelled',
                    'Your imaginary file is safe :)',
                    'error'
                )
            }
        })
    }
</script>
</html>