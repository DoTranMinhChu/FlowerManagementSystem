<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
        <link rel="stylesheet" href="../css/style.css" type="text/css" />
    </head>

    <body>
        <jsp:useBean id="OrderDAO" scope="page" class="chudtm.DAO.OrderDAO"/>
        <c:set var="account" scope="page" value="${sessionScope.account}" />
        <c:choose>
            <c:when test="${account == null}">
                <p>
                    <font color="red">you must login to view personal page</font>
                </p>
                <p></p>
            </c:when>
            <c:otherwise>

                <c:import url="../components/header_loginUser.jsp" />

               
                <section class="table-box">
                    <c:set var="orderid" scope="page" value="${param.orderid}"/>
                    <c:if test="${orderid != null}">

                        <c:set var="orderID" scope="page" value="${Integer.parseInt(orderid.trim())}"/>
                        <c:set var="list" scope="page" value="${OrderDAO.getOrderDetail(orderID)}"/>
                        <c:choose>
                            <c:when test="${list != null && !list.isEmpty()}">
                                <c:set var="money" scope="page"  value="${0.0}"/>
                                <c:forEach var="detail" items="${list}">
                                    <table class="plant-table">
                                        <tr>
                                            <th>Order ID</th>
                                            <th>Plant ID</th>
                                            <th>Plant Name</th>
                                            <th>Image</th>
                                            <th>Price</th>
                                            <th>quantity</th>
                                        </tr>

                                        <tr>
                                            <td>
                                                ${detail.getOrderID()}
                                            </td>
                                            <td>
                                                ${detail.getPlantID()}
                                            </td>
                                            <td>
                                                ${detail.getPlantName()}
                                            </td>
                                            <td><img src="../${detail.getImgPath()}"> </td>
                                            <td>
                                                ${detail.getPrice()}
                                            </td>
                                            <td>
                                                ${detail.getQuantity()}
                                            </td>
                                            <c:set var="money" scope="page" value="${money + (detail.getPrice() * detail.getQuantity())}"/>

                                        </tr>
                                    </table>
                                </c:forEach>
                                <h3>Total money :
                                    ${money}
                                </h3>
                            </c:when>
                            <c:otherwise>
                                <p>You don't have any order</p>
                            </c:otherwise>
                        </c:choose>
                    </c:if>

                </section>
                <c:import url="../components/footer.jsp" />
            </c:otherwise>
        </c:choose>

    </body>

</html>