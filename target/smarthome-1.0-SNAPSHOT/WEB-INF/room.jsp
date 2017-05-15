<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <%@include file="includes/head.jsp" %>
    <body>
        <%@include file="includes/navbar.jsp" %>
        
        <p>Room: 
            <%= request.getParameter("room_id") %>
        </p>
        </br>
        <p> Name:            
               ${dto.getName()}
        </p>
        <p>Dispositivos: </p>       
            <c:forEach items="${dto.getDevices()}" var="device">
                ${device.getName()}<br>
            </c:forEach>
    </body>
</html>
