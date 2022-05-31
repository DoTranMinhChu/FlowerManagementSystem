<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="AccountDAO" class="chudtm.DAO.AccountDAO" scope="page"/>

<c:set var="email" value="" scope="page"/>
<c:set var="password" value="" scope="page"/>
<c:set var="token" value="" scope="page"/>
<c:if test="${cookie != null}">
    <c:forEach var="cookieEl" items="${cookie}">
        <c:if test='${cookieEl.key == "selector" }'>
            <c:set var="token" value="${cookieEl.value.value}" scope="page"/>
            <c:set var="acc" value="${AccountDAO.getAccount(token)}" scope="page"/>
            <c:if test="${acc!=null}">
                <c:set var="email" value="${acc.getEmail()}" scope="page"/>
                <c:set var="password" value="${acc.getPassword()}" scope="page"/>
            </c:if>
        </c:if>
    </c:forEach>
</c:if>


<section onmousedown="clickHiddenSeft(this)" id="loginFormBox" class="center-page hidden">
    <form onmousedown="event.stopPropagation();" action="" method="post" class="form-login">

        <div class="background-form">
            <img src="images/img6.jpg" alt="" class="img-responsive">
        </div>
        <div class="content-form">
            <h3 class="title">Login</h3>
            <font style="color: red;">${requestScope.WARNING==null?"":requestScope.WARNING}</font>
            <div class="content-inner">
                <label for="" class="name-input">Email</label>
                <input type="text" name="txtEmail" class="input-form" value="${email}"></td>
                <label for="" class="name-input">Password</label>
                <input type="password" name="txtPassword" class="input-form" value="${password}"></td>
                <input type="submit" value="login" name="action" class="btn-submit">

                <label for="" class="name-input"> <input type="checkbox" value="saveLogin" name="saveLogin" class="checkbox"> Stayed singed in </label>

            </div>

        </div>
    </form>
</section>
