<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personal Page</title>
        <link rel="stylesheet" href="../css/style.css" type="text/css" />
    </head>

    <body>

        <jsp:useBean id="AccountDAO" class="chudtm.DAO.AccountDAO" scope="page"/>
        <jsp:useBean id="OrderDAO" class="chudtm.DAO.OrderDAO" scope="page"/>
        <c:set var="login" scope="page" value="${false}"/>
        <c:set var="account" scope="page" value="${sessionScope.account}"/>
        <c:set var="name" scope="page" value="${account.getFullName()}"/>
        <c:set var="email" scope="page" value="${account.getEmail()}"/>
        <c:set var="accountID" scope="page" value="${account.getAccID()}" />
        <c:choose>
            <c:when test="${name == null}">
                <c:set var="token" scope="page" value="" />
                <c:forEach var="cookieEl" items="${cookie}">

                    <c:if test='${cookieEl.key == "selector"}'>
                        <c:set var="token" scope="page" value="${cookieEl.value.value}" />
                        <c:set var="acc" scope="page" value="${AccountDAO.getAccount(token)}"/>
                        <c:if test="${acc != null}">
                            <c:set var="name" scope="page" value="${acc.getFullName()}"/>
                            <c:set var="email" scope="page" value="${acc.getEmail()}"/>
                            <c:set var="login" scope="page" value="${true}"/>
                        </c:if>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:set var="login" scope="page" value="${true}"/>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${login}">
                <c:import url="../components/header_loginUser.jsp"/>
                <c:import url="../components/profile.jsp"/>    

                <div class="form-box">
                    <form action="" method="post" style="display:flex; justify-content: space-between" class="form-search">
                        <div>
                            <input type="text" name="keyword" value="${keyword == null ? "" : keyword}">
                            <select name="searchby">
                                <option value="orderID" ${searchby=="orderID"?'selected=""':""}>by order ID</option>
                                <option value="status" ${searchby=="status"?'selected=""':""}>by order's status</option>
                                <option value="accID" ${searchby=="accID"?'selected=""':""}>by account ID</option>
                            </select>

                            <input type="hidden" value="user/personalPage.jsp" name="page">
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
                            <input type="hidden" value="user/personalPage.jsp" name="page">
                            <button type="submit" value="../search" name="action" class="btn-submit">Search</button>

                        </div>
                    </form>
                </div>
                <c:choose>
                    <c:when test="${keyword == null && searchby == null && orderDate==null && shipdate==null && searchdateby == null}">
                        <c:set var="list" scope="page" value='${OrderDAO.getOrders(email)}'/>
                    </c:when>   <c:otherwise>
                        <c:set var="list" scope="page" value='${OrderDAO.getOrders(accountID,keyword,searchby,fromDate,toDate,searchdateby)}'/>
                    </c:otherwise>
                </c:choose>


                <section class="table-box">
                    <c:set var="status" scope="page" value='${["", "processing", "completed", "canceled"]}'/>
                    <c:choose>
                        <c:when test="${list != null && !list.isEmpty()}">

                            <c:set var="listSize" value="${list.size()}" scope="page" />
                            <c:set var="pageSize" value="${5}" scope="page" />
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
                                    <th>action</th>
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
                                            ${status[list[i].getStatus()]} 
                                            <br/>
                                            <form action="" method="post" class="clear">
                                                <input type="hidden" name="orderID" value="${list[i].getOrderID()}">
                                                <input type="hidden" name="status" value="${list[i].getStatus()}">
                                                <input type="hidden" name="action" value="../updateStatusOrder">
                                                <c:choose>
                                                    <c:when test="${list[i].getStatus() == 1}">
                                                        <input class="buttonToLink clear" type="submit" value="cancel order"> 
                                                    </c:when>
                                                    <c:when test="${list[i].getStatus() == 3}">
                                                        <input class="buttonToLink clear" type="submit" value="Order again"> 
                                                    </c:when>
                                                </c:choose>
                                            </form>

                                        </td>

                                        <td>
                                            <form action="orderDetail"  class="clear">

                                                <input type="hidden" name="orderid" value="${list[i].getOrderID()}">
                                                <input class="buttonToLink clear" type="submit" value="Detail"> 
                                            </form>
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
                                <jsp:param name="page" value="user/personalPage.jsp" /> 
                                <jsp:param name="action" value="../search" /> 
                            </jsp:include>
                        </c:when>
                        <c:otherwise>
                            <p>You don't have any order</p>
                        </c:otherwise>

                    </c:choose>
                </section>

            </c:when>
            <c:otherwise>
                <c:import url="../components/header.jsp"/>
                <font color="red">you must login to view personal page</font>
            </c:otherwise>
        </c:choose>
        <c:import url="../components/footer.jsp"/>
        <script src="../js/script.js"></script>
    </body>

</html>