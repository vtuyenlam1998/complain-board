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
    <title>Edit Complain</title>
</head>
<body>
<h2>Edit Complain Form</h2>
<form method="post" action="/complain/edit">
    <fieldset>
        <legend>
            Complain Form <c:out value="${complain.id}"/>
        </legend>
        <table class="table table-striped">
            <tr>
                <input type="hidden" id="id" name="id" size="45" title="letter and number allowed" value="${complain.id}">
                <th scope="row"><label for="title">Title:</label></th>
                <td><input type="text" id="title" name="title" size="45" title="letter and number allowed" value="${complain.title}"></td>
            </tr>
            <tr>
                <th scope="row"><label for="comment">Comment:</label></th>
                <td><input type="text" id="comment" name="comment" size="45" title="letter and number allowed" value="${complain.comment}"></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <a href="/complain"><button  type="button" class="btn btn-success">Cancel</button></a>
                    <button type="submit" class="btn btn-primary">Edit</button>
                </td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
