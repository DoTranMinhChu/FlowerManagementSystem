<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="idFormComfirm" scope="page" value="confirmUpdateBox${param.id}"/>
<section onmousedown="clickHiddenSeft(this)" id="cateFormBox${param.id}" class="center-page hidden">
    <div  onmousedown="event.stopPropagation();" class="plant-box">
        <form action="" method="post" class="inner-plant-box">
            <div class="img-box" style="padding: 0;">
                <img class="img-responsive" src="../images/background.jpg" alt="NoneImgplant">
            </div>
            <div class="infomation-box">
                <div class="title-box">
                    <h1 class="title">Information category</h1>
                </div>
                <div class="element-box">
                    <div class="content-box">
                        <div class="item">
                            <h1 class="itemName">Category ID</h1>
                            <h3 class="itemValue">${param.id}</h3>
                        </div>
                    </div>
                </div>
                <div class="element-box">
                    <div class="content-box">
                        <div class="item">
                            <h1 class="itemName">Name</h1>
                            <input class="itemInput" type="text" value="${param.name}" name="cateNameChange" required="" />
                        </div>
                    </div>
                </div>

                <div class="input-box">
                    <input type="hidden" name="cateID" value="${param.id}" />
                    <input type="hidden" name="action" value="../updateCategory" />
                    <input type="button" value="${param.actionUpdate}" class="btn btn-save" onclick="clickShow('${idFormComfirm}')"/>
                    <input type="reset" value="reset" class="btn btn-reset">  
                </div>
            </div>
            <jsp:include page="confirmBox.jsp">
                <jsp:param name="idForm" value="${idFormComfirm}" />
                <jsp:param name="title" value="${param.titleConfirm}" />
                <jsp:param name="message" value="${param.messageConfirm}" />
                <jsp:param name="valueYes" value="${param.actionUpdate}" />
                <jsp:param name="nameYes" value="actionUpdate" />
            </jsp:include>
        </form>
    </div>
</section>