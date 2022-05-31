<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Plant Shop</title>
        <link rel="stylesheet" href="css/style.css" />
    </head>

    <body>

        <c:import url="./components/header.jsp"/>
        <c:import url="./components/login.jsp"/>        
        <c:import url="./components/register.jsp"/>   
        <c:import url="./components/notifications.jsp"/>

        <jsp:useBean id="PlantDAO" scope="page" class="chudtm.DAO.PlantDAO"/>

        <c:set var="keyword" scope="page" value="${keyword}"/>
        <c:set var="searchby" scope="page" value="${searchby}"/>
        <c:set var="tmp" scope="page" value='${["out of stock", "available"]}'/>
        <c:set var="list" scope="page" value='${PlantDAO.getPlants(keyword, searchby)}'/>
        <c:if test="${list != null && !list.isEmpty()}">

            <c:set var="listSize" value="${list.size()}" scope="page" />
            <c:set var="pageSize" value="${8}" scope="page" />
            <fmt:parseNumber var = "pageNum" integerOnly = "true" type = "number" value = "${(listSize + pageSize - 1) / pageSize}" />
            <c:set var="index" value="${index==null? 1: index}" scope="page"/>
            <c:set var="begin" value="${pageSize * (index-1)}" scope="page" />
            <c:set var="end" value="${pageSize * index - 1}" scope="page" />
            <c:set var="end" value="${end>=listSize?listSize-1:end}" scope="page" />
            <div class="card-list">
                <c:forEach var = "i" begin = "${begin}" end = "${end}">
                    <div class="card-box">
                        <div class="card-outter">
                            <div class="card-inner">
                                <div class="img-box"><img src="${list[i].getImgpath()}" class=" img-responsive" /></div>
                                <div class="content-box">
                                    <div class="name">
                                        ${list[i].getName()}
                                    </div>
                                    <div class="category">Category:
                                        ${list[i].getCatename()}
                                    </div>
                                    <div class="status-box">
                                        <div class="price">Price:
                                            ${list[i].getPrice()} 
                                        </div>
                                        <div class="status">
                                            ${tmp[list[i].getStatus()]}
                                        </div>
                                    </div>

                                    <c:if test="${list[i].getStatus()==1}">
                                        <div class="addCart">
                                            <form method="post"> 
                                                <input type="hidden" name="pid" value="${list[i].getId()}"> 
                                                <button class="buttonToLink" type="submit" name="action" value="addToCart">Add to cart</button>
                                            </form>
                                            
                                        </div>
                                    </c:if>

                                </div>
                                <c:if test="${list[i].getStatus()==0}">
                                    <div class="wp-sold-out-strip">SOLD OUT</div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <jsp:include page="./components/paging.jsp">
                <jsp:param name="index" value="${index}" />
                <jsp:param name="pageNum" value="${pageNum}" />
                <jsp:param name="keyword" value="${keyword}" />
                <jsp:param name="searchby" value="${searchby}" />
                <jsp:param name="searchdateby" value="${searchdateby}" />
                <jsp:param name="fromDate" value="${fromDate}" />
                <jsp:param name="toDate" value="${toDate}" /> 
                <jsp:param name="page" value="index.jsp" /> 
                <jsp:param name="action" value="search" /> 
            </jsp:include>
        </c:if>

        <c:import url="./components/footer.jsp"/>

  <script src="./js/script.js"></script>
    </body>

</html>