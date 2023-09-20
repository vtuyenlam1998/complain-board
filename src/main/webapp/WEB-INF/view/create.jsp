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
    <title>Create Complain</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h2>Add New Complain</h2>
<form method="post" action="/complain/create">
    <fieldset>
        <legend>
            Complain Form
        </legend>
        <table>
            <tr>
                <td><label for="title">Title:</label></td>
                <td><input type="text" id="title" name="title" size="45" title="letter and number allowed"></td>
            </tr>
            <tr>
                <td><label for="comment">Comment:</label></td>
                <td><input type="text" id="comment" name="comment" size="45" title="letter and number allowed"></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <a href="/complain"><button type="button">Cancel</button></a>
                    <button type="submit">Create</button>
                </td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
