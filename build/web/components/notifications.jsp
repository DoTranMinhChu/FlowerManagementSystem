<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listNotifications" value="${requestScope.listNotifications}" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="ncf-container nfc-top-right">
    <c:forEach items="${listNotifications}" var="notification">
        <div class="ncf ${notification.getType()}"><button onclick="remove(this);">X</button>
            <p class="ncf-title">${notification.getTitle()}</p>
            <p class="nfc-message">${notification.getMessage()}</p>
        </div>
    </c:forEach>
</div>

<c:remove var="listNotifications" scope="request"/>