<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Orders</title>
        <link rel="stylesheet" href="../css/style.css" />
    </head>
    <body>
        <c:import url="../components/header_loginAdmin.jsp"/>
        <jsp:useBean id="OrderDAO" scope="page" class="chudtm.DAO.OrderDAO"/>

        <div class="form-box">
            <form action="" method="post" style="display:flex; justify-content: space-between" class="form-search">
                <div>
                    <input type="text" name="keyword" value="${keyword == null ? "" : keyword}">
                    <select name="searchby">
                        <option value="orderID" ${searchby=="orderID"?'selected=""':""}>by order ID</option>
                        <option value="status" ${searchby=="status"?'selected=""':""}>by order's status</option>
                        <option value="accID" ${searchby=="accID"?'selected=""':""}>by account ID</option>
                    </select>

                    <input type="hidden" value="admin/manageOrders.jsp" name="page">
                    <button type="submit" value="../search" name="action" class="btn-submit">Search</button>
                </div>
                <div>
                    <label for="">From</label>  
                    <input type="date" pattern="yyyy/mm/dd" name="fromDate" value="${fromDate == null ? "" : fromDate}">
                    <label for="">To</label>
                    <input type="date" name="toDate" value="${toDate == null ? "" : toDate}">
                    <select name="searchdateby" s>
                        <option value="orderDate" ${searchby=="orderDate"?'selected=""':""}>by order date</option>
                        <option value="shipdate" ${searchby=="shipdate"?'selected=""':""}>by ship date</option>                      
                    </select>
                    <input type="hidden" value="admin/manageOrders.jsp" name="page">
                    <button type="submit" value="../search" name="action" class="btn-submit">Search</button>
                  
                </div>
            </form>
        </div>



        <c:choose>
            <c:when test="${keyword == null && searchby == null && orderDate==null && shipdate==null && searchdateby == null}">
                <c:set var="list" scope="page" value='${OrderDAO.getAllOrder()}'/>
            </c:when>
            <c:otherwise>
                <c:set var="list" scope="page" value='${OrderDAO.getOrders(keyword, searchby,fromDate,toDate,searchdateby)}'/>
            </c:otherwise>
        </c:choose>
        <section class="table-box">
            <c:set var="status" scope="page" value='${["", "processing", "completed", "canceled"]}'/>
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
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th>Ship Date</td>
                            <th>Order's status</th>
                            <th>Account ID</th>
                        </tr>
                        <c:forEach var = "i" begin = "${begin}" end = "${end}">
                            <tr>
                                <td>
                                    ${list[i].getOrderID()}
                                </td>
                                <td>
                                    ${list[i].getOrderDate()}
                                </td>
                                <td>
                                    ${list[i].getShipDate()}
                                </td>
                                <td>
                                    ${list[i].getStatus()}
                                    <br>
                                    <small>${ status[list[i].getStatus()]}</small>

                                </td>
                                <td>
                                    ${list[i].getAccID()}
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
                        <jsp:param name="page" value="admin/manageOrders.jsp" /> 
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
