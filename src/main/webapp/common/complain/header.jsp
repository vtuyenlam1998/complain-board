<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglib.jsp" %>

<!-- Navbar -->
<nav id="main-navbar" class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
    <!-- Container wrapper -->
    <div class="container-fluid">
        <!-- Navbar brand -->
        <a class="navbar-brand mt-2 mt-lg-0" href="/complain">
            <h5 class="pt-1">SH SOFT VINA</h5>
        </a>
        <!-- Toggle button -->
        <button class="navbar-toggler" type="button" data-mdb-toggle="collapse" data-mdb-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fas fa-bars"></i>
        </button>

        <!-- Collapsible wrapper -->
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <!-- Left links -->
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="#">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Team</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Projects</a>
                </li>
            </ul>
            <!-- Left links -->

            <!-- Right elements -->
            <div class="d-flex align-items-center justify-content-start">

                <c:set var="user" value="${sessionScope.user}" />

                <c:set var="role" value="${user.role}" />
                <c:set var="cleanedRole" value="${fn:substringAfter(role, 'ROLE_')}" />
                <c:set var="startIndex" value="${fn:replace(cleanedRole, '_', '')}" />
                <c:set var="startIndexInt" value="${startIndex}" />
                <a class="text-reset me-3" href="#">
                    <span><c:out value="${startIndexInt}"/> | <c:out value="${user.username}"/></span>
                </a>

                </div>
                <div class="dropdown">
                    <a class="dropdown-toggle d-flex align-items-center hidden-arrow" href="#" id="navbarDropdownMenuAvatar" role="button" data-mdb-toggle="dropdown" aria-expanded="false">
                        <img src="data:image/png;base64,${user.image}" class="rounded-circle" height="40" width="40" alt="Avatar" loading="lazy" />
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownMenuAvatar">
                        <li>
                            <a class="dropdown-item" href="#">My profile</a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="#">Settings</a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="/logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- Right elements -->
        </div>
        <!-- Collapsible wrapper -->
    <!-- Container wrapper -->
</nav>
<!-- Navbar -->
