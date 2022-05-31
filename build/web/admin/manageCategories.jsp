<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Categories</title>
        <link rel="stylesheet" href="../css/style.css" type="text/css" />
    </head>
    <body>
        <c:import url="../components/header_loginAdmin.jsp" />
        <jsp:useBean id="PlantDAO" scope="page" class="chudtm.DAO.PlantDAO"/>
        <jsp:useBean id="CategoryDAO" scope="page" class="chudtm.DAO.CategoryDAO"/>

        <div style="display: flex; justify-content: space-between" class="form-box">
            <form action="" method="post" class="form-search">
                <input type="text" name="keyword" value="${keyword == null ? "" : keyword}">
                <select name="searchby" s>
                    <option value="cateID" ${searchby=="cateID"?'selected=""':""}>by category ID</option>
                    <option value="cateName" ${searchby=="cateName"?'selected=""':""}>by category name</option>
                </select>

                <input type="hidden" value="admin/manageCategories.jsp" name="page">
                <button type="submit" value="../search" name="action" class="btn-submit">Search</button>
            </form>
            <input onclick="clickShow('cateFormBox')" type="button" value="Create" class="btn-edc btn-create">

        </div>

        <jsp:include page="../components/categoryForm.jsp">
            <jsp:param name="id" value="" />
            <jsp:param name="name" value="" />
            <jsp:param name="actionUpdate" value="create" /> 
            <jsp:param name="titleConfirm" value="Are you sure?"/> 
            <jsp:param name="messageConfirm" value="Are you sure create the category?" /> 
        </jsp:include>


        <c:set var="list" scope="page" value='${CategoryDAO.getCategories(keyword, searchby)}'/>
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
                            <th>Category ID</th>
                            <th>Category Name</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach var = "i" begin = "${begin}" end = "${end}">
                            <tr>
                                <td>
                                    ${list[i].getCateID()}
                                </td>
                                <td>
                                    ${list[i].getCateName()}
                                </td>
                                <td>            


                                    <input type="button" onclick="clickShow('cateFormBox${list[i].getCateID()}')"  value="update" class="btn-edc btn-edit">
                                    <input type="button" onclick="clickShow('confirmRemove${list[i].getCateID()}')" value="remove" name="actionUpdate" class="btn-edc btn-delete">


                                </td>

                            </tr>                  
                        </c:forEach>
                    </table>   
                    <c:forEach var = "i" begin = "${begin}" end = "${end}">
                        <jsp:include page="../components/categoryForm.jsp">
                            <jsp:param name="id" value="${list[i].getCateID()}" />
                            <jsp:param name="name" value="${list[i].getCateName()}" />
                            <jsp:param name="actionUpdate" value="save" /> 
                            <jsp:param name="titleConfirm" value="Are you sure?"/> 
                            <jsp:param name="messageConfirm" value="Are you sure you will change the category's information?" /> 
                        </jsp:include>
                        <form action="" method="post">
                            <input type="hidden" name="cateID" value="${list[i].getCateID()}" />
                            <input type="hidden" name="action" value="../updateCategory" />
                            <jsp:include page="../components/confirmBox.jsp">
                                <jsp:param name="idForm" value="confirmRemove${list[i].getCateID()}" />
                                <jsp:param name="title" value="Are you sure?" />
                                <jsp:param name="message" value="Are you sure remove category?" />
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
                        <jsp:param name="page" value="admin/manageCategories.jsp" /> 
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
