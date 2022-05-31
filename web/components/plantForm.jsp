<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="CategoryDAO" scope="page" class="chudtm.DAO.CategoryDAO" />
<c:set var="categories" scope="page" value="${CategoryDAO.getCategories()}" />
<c:set var="defaultImg" scope="page" value="${param.defaultImg}" />
<c:set var="idFormComfirm" scope="page" value="confirmUpdateBox${param.id}"/>
<section onmousedown="clickHiddenSeft(this)" id="plantFormBox${param.id}" class="center-page hidden">
    <div  onmousedown="event.stopPropagation();" class="plant-box">
        <form action="" method="post" class="inner-plant-box" enctype="multipart/form-data">
            <div class="img-box">
                <div class="img-plant">
                    <img id="my-img${param.id}" class="img-responsive" src="${param.defaultImg}" alt="NoneImgplant">
                </div>
            </div>
            <div class="infomation-box">
                <div class="title-box">
                    <h1 class="title">Infomation plant</h1>
                </div>
                <div class="element-box">   
                    <div class="content-box">
                        <div class="item">
                            <h1 class="itemName">Plant ID</h1>
                            <h3 class="itemValue">${param.id}</h3>
                        </div>
                    </div>
                </div>
                <div class="element-box">
                    <div class="content-box">
                        <div class="item">
                            <h1 class="itemName">Name</h1>
                            <input class="itemInput" type="text" value="${param.name}" name="fullNameChange" required="" />
                        </div>
                        <div class="item">
                            <h1 class="itemName">Categories</h1>
                            <select name="categoryChange" class="itemSelect">
                                <c:forEach var="category" items="${categories}">
                                    <option  ${param.cateID==category.getCateID()?"selected":""} value="${category.getCateID()}">${category.getCateName()}</option>
                                </c:forEach>


                            </select>
                        </div>
                        <div class="item">
                            <h1 class="itemName">Image path</h1>
                            <div class="upload-box">
                                <label for="input-upload${param.id}" class="label-upload">Upload Image</label>
                                <input type="file" name="imgUpload" id="input-upload${param.id}" class="input-upload" accept=".png, .jpg, .jpeg" onchange="showPreview(event, 'my-img${param.id}');">
                                <input type="hidden" name="oldPathImg" value="${param.defaultImg}">
                            </div>

                        </div>
                    </div>
                </div>
                <div class="element-box">
                    <div class="content-box">
                        <div class="item">
                            <h1 class="itemName">Price</h1>
                            <input class="itemInput" type="text" value="${param.price}" name="priceChange" required="" />
                        </div>
                        <div class="item">
                            <h1 class="itemName">Status</h1>
                            <select name="statusChange" class="itemSelect">
                                <option ${param.status==1?"selected":""} value="1">Active</option>
                                <option ${param.status==0?"selected":""} value="0">Inactive</option>           
                            </select>
                        </div>
                    </div>
                </div>

                <div class="element-box">
                    <div class="content-box">
                        <div class="item">
                            <h1 class="itemName">Description</h1>
                            <textarea name="descriptionChange" rows="5" cols="45" class="itemTextarea">${param.description}</textarea>
                        </div>
                    </div>
                </div>
                <div class="input-box">
                    <input type="hidden" name="plantId" value="${param.id}" />
                    <input type="hidden" name="action" value="../updatePlant" />
                    <input type="button" value="${param.actionUpdate}" class="btn btn-save" onclick="clickShow('${idFormComfirm}')">
                    <input type="reset" value="reset" class="btn btn-reset" onclick="setSrcImg('my-img${param.id}', '${param.defaultImg}');" >  
                </div>
            </div>
            <jsp:include page="../components/confirmBox.jsp">
                <jsp:param name="idForm" value="${idFormComfirm}" />
                <jsp:param name="title" value="${param.titleConfirm}" />
                <jsp:param name="message" value="${param.messageConfirm}" />
                <jsp:param name="valueYes" value="${param.actionUpdate}" />
                <jsp:param name="nameYes" value="actionUpdate" />
            </jsp:include>
        </form>
    </div>
</section>