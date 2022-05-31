<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Accounts</title>
        <link rel="stylesheet" href="../css/style.css" />
    </head>
    <body>
        <c:import url="../components/header_loginAdmin.jsp"/>
        <jsp:useBean id="AccountDAO" scope="page" class="chudtm.DAO.AccountDAO"/>

        <div class="form-box">
            <form action="" method="post" class="form-search">
                <input type="text" name="keyword" value="${keyword == null ? "" : keyword}">
                <select name="searchby">
                    <option value="accID" ${searchby=="accID"?'selected=""':""}>by ID</option>
                    <option value="email" ${searchby=="email"?'selected=""':""}>by email</option>
                    <option value="fullname" ${searchby=="fullname"?'selected=""':""}>by full name</option>
                    <option value="status" ${searchby=="status"?'selected=""':""}>by status</option>
                    <option value="phone" ${searchby=="phone"?'selected=""':""}>by phone</option>
                    <option value="role" ${searchby=="role"?'selected=""':""}>by role</option>
                </select>
                <input type="hidden" value="admin/manageAccounts.jsp" name="page">
                <button type="submit" value="../search" name="action" class="btn-submit">Search</button>
                
            </form>
        </div>




        <c:set var="list" scope="page" value='${AccountDAO.getAccounts(keyword, searchby)}'/>

        <section class="table-box">
            <c:set var="status" scope="page" value='${["inActive", "Active"]}'/>
            <c:choose>
                <c:when test="${list != null && !list.isEmpty()}">

                    <c:set var="listSize" value="${list.size()}" scope="page" />
                    <c:set var="pageSize" value="${8}" scope="page" />
                    <fmt:parseNumber var = "pageNum" integerOnly = "true" type = "number" value = "${(listSize + pageSize - 1) / pageSize}" />
                    <c:set var="index" value="${index==null? 1: index}" scope="page"/>
                    <c:set var="begin" value="${pageSize * (index-1)}" scope="page" />
                    <c:set var="end" value="${pageSize * index - 1}" scope="page" />
                    <c:set var="end" value="${end>=listSize?listSize-1:end}" scope="page" />

                    <table class="order-table"> 
                        <tr>
                            <th>ID</th>
                            <th>Email</th>
                            <th>Full name</th>
                            <th>Status</th>
                            <th>Phone</th>
                            <th>Role</th>
                            <th>Action</th>
                        </tr>

                        <c:forEach var = "i" begin = "${begin}" end = "${end}">
                            <tr> 
                                <td><c:out value="${list[i].getAccID()}"></c:out></td>
                                <td><c:out value="${list[i].getEmail()}"></c:out></td>
                                <td><c:out value="${list[i].getFullName()}"></c:out></td>
                                <td>${list[i].getStatus()}
                                    <br>
                                    <small>${ status[list[i].getStatus()]}</small>

                                </td>

                                <td> 
                                    ${list[i].getPhone()}
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${list[i].getRole() eq 1}">admin</c:when>
                                        <c:otherwise>user</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>

                                    <c:if test="${list[i].getRole() eq 0}">

                                        <form action="" method="post" class="clear">
                                            <input type="hidden" name="email" value="${list[i].getEmail()}" />
                                            <input type="hidden" name="status" value="${list[i].getStatus()}" />
                                            <input type="hidden" name="action" value="../updateStatusAccount" />
                                            <input type="submit" value="Block/UnBlock" class="btn-hyperlink"/>
                                        </form>


                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                    <jsp:include page="../components/paging.jsp">
                        <jsp:param name="index" value="${index}" />
                        <jsp:param name="pageNum" value="${pageNum}" />
                        <jsp:param name="keyword" value="${keyword}" />
                        <jsp:param name="searchby" value="${searchby}" />
                        <jsp:param name="searchdateby" value="${searchdateby}" />
                        <jsp:param name="fromDate" value="${fromDate}" />
                        <jsp:param name="toDate" value="${toDate}" /> 
                        <jsp:param name="page" value="admin/manageAccounts.jsp" /> 
                        <jsp:param name="action" value="../search" /> 
                    </jsp:include>
                </c:when>
                <c:otherwise>
                    <p>Couldn't find any results</p>
                </c:otherwise>
            </c:choose>


        </section>
        <c:import url="../components/footer.jsp" />
    </body>
</html>
