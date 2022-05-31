<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pagination-box">
    <form action="" method="post" class="pagination">
        <c:if test="${param.index>1}">
            <input type="submit" class="item" value="-" name="changeIndex"/>
        </c:if>

        <c:forEach var="i" begin="1" end="${param.pageNum}">
            <input type="submit" class="item ${i==param.index?'active':''}" value="${i}" name="index" />
        </c:forEach>
        <c:if test="${param.index<param.pageNum}">
            <input type="submit" class="item" value="+" name="changeIndex"/>
        </c:if>

        <input type="hidden" value="${param.index}" name="index"/>
        <input type="hidden" value="${param.keyword}" name="keyword"/>
        <input type="hidden" value="${param.searchby}" name="searchby"/>
        <input type="hidden" value="${param.searchdateby}" name="searchdateby"/>
        <input type="hidden" value="${param.fromDate}" name="fromDate"/>
        <input type="hidden" value="${param.toDate}" name="toDate"/>
        <input type="hidden" value="${param.page}" name="page"/>
        <input type="hidden" value="${param.action}" name="action"/>
    </form>
</div>





<%--<jsp:include page="paging.jsp">
    <jsp:param name="index" value="${index}" />
    <jsp:param name="pageNum" value="${pageNum}" />
    <jsp:param name="keyword" value="${keyword}" />
    <jsp:param name="searchby" value="${searchby}" />
    <jsp:param name="searchdateby" value="${searchdateby}" />
    <jsp:param name="fromDate" value="${fromDate}" />
    <jsp:param name="toDate" value="${toDate}" /> 
    <jsp:param name="page" value="index.jsp" /> 
</jsp:include>--%>