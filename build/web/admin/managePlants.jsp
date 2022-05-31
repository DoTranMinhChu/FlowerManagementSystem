<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Plants</title>
        <link rel="stylesheet" href="../css/style.css" type="text/css" />
    </head>
    <body>

        <c:import url="../components/header_loginAdmin.jsp" />
        <c:import url="../components/notifications.jsp"/>
        <jsp:useBean id="PlantDAO" scope="page" class="chudtm.DAO.PlantDAO"/>
        <jsp:useBean id="CategoryDAO" scope="page" class="chudtm.DAO.CategoryDAO"/>

        <div style="display: flex; justify-content: space-between" class="form-box">
            <form action="" method="post" class="form-search">
                <input type="text" name="keyword" value="${keyword== null ? "" : keyword}">
                <select name="searchby" s>
                    <option value="PID" ${searchby=="PID"?'selected=""':""}>by plant ID</option>
                    <option value="PName" ${searchby=="PName"?'selected=""':""}>by plant name</option>
                    <option value="price" ${searchby=="price"?'selected=""':""}>by price</option>
                    <option value="status" ${searchby=="status"?'selected=""':""}>by plant's status</option>
                    <option value="CateName" ${searchby=="CateName"?'selected=""':""}>by categories name</option>
                </select>
                <input type="hidden" value="managePlants.jsp" name="page">
                <input type="submit" value="search" name="action" class="btn-submit">
            </form>
            <input onclick="clickShow('plantFormBox')" type="button" value="Create" class="btn-edc btn-create">

        </div>

        <jsp:include page="../components/plantForm.jsp"> 
            <jsp:param name="defaultImg" value="../images/imgAdmin.png" />
            <jsp:param name="id" value="" />
            <jsp:param name="name" value="" />
            <jsp:param name="cateID" value="" /> 
            <jsp:param name="description" value="" /> 
            <jsp:param name="actionUpdate" value="create" /> 
            <jsp:param name="titleConfirm" value="Confirm Diaglog"/> 
            <jsp:param name="messageConfirm" value="Are you sure?" /> 
        </jsp:include>



        <c:set var="list" scope="page" value='${PlantDAO.getPlants(keyword, searchby)}'/>

        <c:set var="categories" scope="page" value="${CategoryDAO.getCategories()}"/>
        <section class="table-box">
            <c:set var="status" scope="page" value='${["inActive", "Active"]}'/>
            <c:choose>
                <c:when test="${list != null && !list.isEmpty()}">

                    <c:set var="listSize" value="${list.size()}" scope="page" />
                    <c:set var="pageSize" value="${5}" scope="page" />
                    <fmt:parseNumber var = "pageNum" integerOnly = "true" type = "number" value = "${(listSize + pageSize - 1) / pageSize}" />
                    <c:set var="index" value="${index==null? 1: index}" scope="page"/>
                    <c:set var="begin" value="${pageSize * (index-1)}" scope="page" />
                    <c:set var="end" value="${pageSize * index - 1}" scope="page" />
                    <c:set var="end" value="${end>=listSize?listSize-1:end}" scope="page" />

                    <table class="plant-table">
                        <tr>
                            <th>Plant ID</th>
                            <th>Plant Name</th>
                            <th>Image</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Categories</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach var = "i" begin = "${begin}" end = "${end}">
                            <tr>
                                <td>
                                    ${list[i].getId()}
                                </td>
                                <td>
                                    ${list[i].getName()}
                                </td>
                                <td>
                                    <img src="../${list[i].getImgpath()}">
                                </td>
                                <td>
                                    ${list[i].getPrice()}
                                </td>
                                <td>
                                    ${list[i].getDescription()}
                                </td>
                                <td>
                                    ${list[i].getStatus()}
                                    <br>
                                    <small> ${status[list[i].getStatus()]}</small>

                                </td>
                                <td>
                                    ${list[i].getCatename()}
                                </td>
                                <td>            
                                    <input type="button" onclick="clickShow('plantFormBox${list[i].getId()}')"  value="update" class="btn-edc btn-edit">
                                    <input type="button" onclick="clickShow('confirmRemove${list[i].getId()}')" value="remove" name="actionUpdate" class="btn-edc btn-delete">
                                </td>

                            </tr>                  
                        </c:forEach>

                    </table> 
                    <c:forEach var = "i" begin = "${begin}" end = "${end}">
                        <jsp:include page="../components/plantForm.jsp">
                            <jsp:param name="defaultImg" value="../${list[i].getImgpath()}" />
                            <jsp:param name="id" value="${list[i].getId()}" />
                            <jsp:param name="name" value="${list[i].getName()}" />
                            <jsp:param name="cateID" value="${list[i].getCateid()}" /> 
                            <jsp:param name="price" value="${list[i].getPrice()}"/>
                            <jsp:param name="status" value="${list[i].getStatus()}"/>
                            <jsp:param name="description" value="${list[i].getDescription()}" /> 
                            <jsp:param name="actionUpdate" value="save" />
                            <jsp:param name="titleConfirm" value="Are you sure?"/>  
                            <jsp:param name="messageConfirm" value="Are you sure you will change the plant's information?" /> 
                        </jsp:include>
                        <form action="" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="plantId" value="${list[i].getId()}" />
                            <input type="hidden" name="action" value="../updatePlant" />
                            <jsp:include page="../components/confirmBox.jsp">
                                <jsp:param name="idForm" value="confirmRemove${list[i].getId()}" />
                                <jsp:param name="title" value="Are you sure?" />
                                <jsp:param name="message" value="Are you sure remove plant?" />
                                <jsp:param name="valueYes" value="remove" />
                                <jsp:param name="nameYes" value="actionUpdate" />
                            </jsp:include>
                        </form>

                    </c:forEach>


                    <jsp:include page="../components/paging.jsp">
                        <jsp:param name="index" value="${index}" />
                        <jsp:param name="pageNum" value="${pageNum}" />
                        <jsp:param name="keyword" value="${keyword}" />
                        <jsp:param name="searchby" value="${searchby}" />
                        <jsp:param name="searchdateby" value="${searchdateby}" />
                        <jsp:param name="fromDate" value="${fromDate}" />
                        <jsp:param name="toDate" value="${toDate}" /> 
                        <jsp:param name="page" value="admin/managePlants.jsp" /> 
                        <jsp:param name="action" value="../search" /> 
                    </jsp:include>

                </c:when>
                <c:otherwise>
                    <p>Couldn't find any results</p>
                </c:otherwise>
            </c:choose>
        </section>

        <c:import url="../components/footer.jsp" />
        <script src="../js/script.js"></script>
    </body>
</html>
