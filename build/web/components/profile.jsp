<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="AccountDAO" class="chudtm.DAO.AccountDAO" scope="page" />
<c:set var="account" scope="page" value="${AccountDAO.getInfoAccount(sessionScope.email)}" />
<c:set var="listRole" scope="page" value='${["User","Admin"]}' />
<c:set var="listImgRole" scope="page" value='${["../images/imgUser.png","../images/imgAdmin.png"]}' />
<c:set var="role" scope="page" value="${account.getRole()}" />
<c:set var="email" scope="page" value="${account.getEmail()}" />
<c:set var="fullName" scope="page" value="${account.getFullName()}" />
<c:set var="password" scope="page" value="${account.getPassword()}" />
<c:set var="phone" scope="page" value="${account.getPhone()}" />

<section onmousedown="clickHiddenSeft(this)" id="profileFormBox" class="center-page hidden">
    <div onmousedown="event.stopPropagation();" class="profile-box">
        <form action="" method="get" class="inner-profile-box">
            <div class="role-box">
                <div class="img-box">
                    <img class="img-responsive" src="${listImgRole[role]}">
                </div>
                <div class="content-box">
                    <h1>${listRole[role]}</h1>
                </div>
            </div>
            <div class="infomation-box">
                <div class="title-box">
                    <h1 class="title">Infomation</h1>
                </div>
                <div class="element-box">
                    <div class="content-box">
                        <div class="item">
                            <h1 class="itemName">Email</h1>
                            <h3 class="itemValue">${email}</h3>
                        </div>

                        <div class="item">
                            <h1 class="itemName">Password</h1>
                            <input class="itemInput" type="password" value="${password}" name="passChange" required="" />
                        </div>

                    </div>
                </div>
                <div class="element-box">
                    <div class="content-box">
                        <div class="item">
                            <h1 class="itemName">Name</h1>
                            <input class="itemInput" type="text" value="${fullName}" name="fullNameChange" required="" />
                        </div>
                        <div class="item">
                            <h1 class="itemName">Phone</h1>
                            <input class="itemInput" type="text" value="${phone}" name="phoneNameChange" required="" />
                        </div>
                    </div>
                </div>
                <div class="input-box">
                    <input type="hidden" name="email" value="${email}" />
                    <input type="hidden" name="action" value="../updateAccount" />
                    <input type="submit" value="save" class="btn btn-save" />
                    <input type="reset" value="reset" class="btn btn-reset">
                </div>
            </div>
        </form>
    </div>
</section>