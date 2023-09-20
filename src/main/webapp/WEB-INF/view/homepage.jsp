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
    <title>Complain List</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        table {
            border: 1px solid #000;
        }

        th, td {
            border: 1px dotted #555;
        }

        .message {
            color: green;
        }
    </style>
    <!-- Font Awesome -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
    />
    <!-- Google Fonts -->
    <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
    />
    <!-- MDB -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.1/mdb.min.css"
            rel="stylesheet"
    />
    <!-- MDB -->
    <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.1/mdb.min.js"
    ></script><!-- MDB -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>

<h1 class="text-decoration-underline text-center">Complain List</h1>
<a href="//login?logout=true"><button>Log out</button></a>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Title</th>
        <th scope="col">Creator</th>
        <th scope="col">Time Creation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${requestScope.complains.complainList}">
        <tr>
            <th scope="row">
                <c:out value="${c.id}"/>
            </th>
            <td>
                <a href="/complain/${c.id}">${c.title}</a>
            </td>
            <td>
                <c:out value="${c.comment}"/>
            </td>
            <td>
                <c:out value="${c.timeCreation}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
There are ${requestScope.complains.complainList.size()} complain (s) in list.
<div class="flex" style="justify-content: center">
    <p><c:if test="${message} != null"/><span class="message">${message}</span></p>
    <form action="/complain/create" method="get">
        <button class="btn btn-info" style="text-align: center" type="submit">Create</button>
    </form>
</div>
<div class="d-flex justify-content-center">
        <a href="/complain?page=0">
            <button type="button" class="btn btn-primary btn-floating">
                <i class="fa-solid fa-backward"></i>
            </button>
        </a>
    <c:if test="${complains.hasPrevious}">
    <a href="/complain?page=${complains.pageNumber - 1}">
        <button type="button" class="btn btn-primary btn-floating">
            <i class="fa-solid fa-caret-left"></i>
        </button>
    </a>
    </c:if>
    <c:if test="${(complains.pageNumber + 2 > complains.totalPages) && (complains.pageNumber > 0)}">
        <a href="/complain?page=${complains.pageNumber-2}">
            <button type="button" class="btn btn-outline-info btn-link btn-floating" data-mdb-ripple-color="blue">
                <span>${complains.pageNumber - 1}</span>
            </button>
        </a>
    </c:if>
    <c:if test="${(complains.pageNumber + 2 >= complains.totalPages) && (complains.pageNumber > 0)}">
        <a href="/complain?page=${complains.pageNumber-1}">
            <button type="button" class="btn btn-outline-info btn-link btn-floating" data-mdb-ripple-color="blue">
                <span>${complains.pageNumber}</span>
            </button>
        </a>
    </c:if>
<c:if test="${complains.totalPages - 1 >= complains.pageNumber}">
        <a href="/complain?page=${complains.pageNumber}">
            <button type="button" class="btn btn-outline-info btn-link btn-floating btn-primary" data-mdb-ripple-color="blue">
                <span>${complains.pageNumber + 1}</span>
            </button>
        </a>
</c:if>
<c:if test="${complains.totalPages > (complains.pageNumber + 1)}">
        <a href="/complain?page=${complains.pageNumber + 1}">
            <button type="button" class="btn btn-outline-info btn-link btn-floating" data-mdb-ripple-color="blue">
                <span>${complains.pageNumber + 2}</span>
            </button>
        </a>
</c:if>
    <c:if test="${complains.totalPages > (complains.pageNumber + 2)}">
        <a href="/complain?page=${complains.pageNumber + 2}">
            <button type="button" class="btn btn-outline-info btn-link btn-floating" data-mdb-ripple-color="blue">
                <span>${complains.pageNumber + 3}</span>
            </button>
        </a>
    </c:if>
<c:if test="${complains.hasNext}">
    <a href="/complain?page=${complains.pageNumber + 1}">
        <button type="button" class="btn btn-primary btn-floating">
            <i class="fa-solid fa-caret-right fa-lg"></i>
        </button>
    </a>
</c:if>
    <a href="/complain?page=${complains.totalPages - 1}">
        <button type="button" class="btn btn-primary btn-floating">
            <i class="fa-solid fa-forward"></i>
        </button>
    </a>
</div>
</body>
</html>
