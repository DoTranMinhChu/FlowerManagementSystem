
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <c:import url="./components/header.jsp"/>
        <c:import url="./components/login.jsp"/>        
        <c:import url="./components/register.jsp"/>   
        <c:import url="./components/notifications.jsp"/>
        <jsp:useBean id="PlantDAO" scope="page" class="chudtm.DAO.PlantDAO"/>
        <c:set var="cart" scope="page" value="${sessionScope.cart}"/>
        <font style="color: red;">  ${requestScope.WARNING == null ? "" : requestScope.WARNING} </font>
        <c:set var="totalMoney" value="${0}" scope="page"/>
        <c:set var="totalQuanlity" value="${0}" scope="page"/>


        <c:set var="pids" scope="page" value="${cart.keySet()}"/>

        <div class="cart-box">
            <div class="cart-list-box">
                <table class="cart-table">
                    <tr class="row-head">
                        <th class="col-plants">PLANTS</th>
                        <th class="col-quantity">QUANTITY</th>
                        <th class="col-price">PRICE</th>
                        <th class="col-action"></th>
                    </tr>

                    <c:forEach var="pid" items="${pids}">
                        <c:set var="plant" scope="page" value="${PlantDAO.getPlant(pid)}"/>
                        <form action="" method="post">
                            <tr class="row-item">
                                <td class="col-plants">
                                    <div class="product-box">
                                        <div class="img-box">
                                            <img src="${plant.getImgpath()}" class="img-responsive">
                                        </div>
                                        <div class="infomation-box">
                                            <h2>${plant.getName()}</h2>
                                            <h4>Category: ${plant.getCatename()}</h4>
                                        </div>
                                    </div>
                                </td>
                                <td class="col-quantity">
                                    <div class="quantity-box">
                                        <input type="number" value="${cart.get(pid)}" name="quantity" min="1">
                                    </div>

                                </td>
                                <td class="col-price">
                                    <div class="price-box">
                                        <h3><i>${plant.getPrice()}</i></h3>
                                    </div>
                                </td>
                                <td class="col-action">
                                    <div class="action-box">
                                        <input type="hidden" value="updateCart" name="action">
                                        <input type="hidden" value="${pid}" name="pid">
                                        <input type="submit" value="save" name="actionUpdate" class="btn btn-create">
                                        <input type="submit" value="delete" name="actionUpdate" class="btn btn-delete">
                                    </div>
                                </td>
                            </tr>
                        </form>
                        <c:set var="totalMoney" value="${totalMoney+(plant.getPrice()*cart.get(pid))}" scope="page"/>
                        <c:set var="totalQuanlity" value="${totalQuanlity+cart.get(pid)}" scope="page"/>
                    </c:forEach>


                </table>
            </div>
            <div class="bill-box">
                <div class="title-box">
                    <h1 class="title">Order</h1>
                </div>
                <div class="detail-box">
                    <div class="detail-item">
                        <c:set var = "now" value = "<%= new java.util.Date()%>" />
                        <p class="name">Date:</p>
                        <p class="value"><fmt:formatDate pattern = "hh:mm - yyyy/MM/dd" value = "${now}" /></p>
                    </div>
                    <div class="detail-item">
                        <p class="name">Total quantity:</p>
                        <p class="value">${totalQuanlity}</p>
                    </div>
                    <div class="detail-item">
                        <p class="name">Total price:</p>
                        <p class="value">${totalMoney}</p>
                    </div>
                </div>

                <div class="action-box">
                    <c:if test="${cart!=null && pids.size() !=0}">
                        <form action="" method="post">
                            <input type="hidden" value="saveShoppingCart" name="action">
                            <input type="submit" value="Make Purchase" name="actionUpdate" class="btn btn-make">
                            <input type="submit" value="Cancel Purchase" name="actionUpdate" class="btn btn-cancel">
                        </form>
                    </c:if>


                </div>
            </div>
        </div>



        <c:import url="./components/footer.jsp"/>

        <script src="./js/script.js"></script>

    </body>
</html>
