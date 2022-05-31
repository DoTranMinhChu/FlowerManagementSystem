<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="AccountDAO" scope="page" class="chudtm.DAO.AccountDAO" />

<c:set var="account" scope="page" value="${sessionScope.account}"/>

<c:if test="${account != null}">
    <c:set var="name" scope="page" value="${account.getFullName()}"/>
    <c:set var="email" scope="page" value="${account.getEmail()}"/>
    <c:set var="role" scope="page" value="${account.getRole()}"/>

</c:if>

<header>
    <nav class="navbar">
        <div class="left-nav nav-list">
            <div class="logo-box">
                <a href="" class="nav-link"><img src="images/logo.jpg" class="img-responsive"></a>
            </div>
        </div>

        <div class="right-nav nav-list">
            <div class="nav-item"><a href="./" class="nav-link">Home</a></div>
            <c:choose>
                <c:when test="${role == 0}">
                    <div class="nav-item"><a href="user/personalPage" class="nav-link">Personal Page</a></div>
                </c:when>
                <c:when test="${role == 1}">
                    <div class="nav-item"><a href="admin/adminIndex" class="nav-link">Admin Page</a></div>
                </c:when>
                <c:otherwise>
                    <div class="nav-item"  onclick="clickShow('registrationFormBox')"><a href="" class="nav-link none-events">Register</a></div>
                    <div class="nav-item" onclick="clickShow('loginFormBox')"><a href="" class="nav-link none-events">Login</a></div>

                </c:otherwise>
            </c:choose>

            <div class="nav-item"><a href="viewCart" class="nav-link">View cart</a></div>
            <c:if test="${role == 0 || role == 1}">
                <div class="nav-list">
                    <div class="nav-item">Welcome ${sessionScope.account.getFullName()}   | <a href="?action=logout&page=./" class="nav-link">Logout</a></div>
                </div>
            </c:if>

            <div class="form-box">
                <form action="" method="post" class="form-search">
                    <input type="text" name="keyword" value="${keyword == null ? "" :keyword}">
                    <select name="searchby">
                        <option value="PName" ${searchby=="PName"?'selected=""':""}>by name</option>
                        <option value="CateName" ${searchby=="CateName"?'selected=""':""}>by category</option>
                    </select>
                    <input type="hidden" value="index.jsp" name="page">
                    <input type="submit" value="search" name="action" class="btn-submit">
                </form>
            </div>
        </div>
    </nav>



</header>
