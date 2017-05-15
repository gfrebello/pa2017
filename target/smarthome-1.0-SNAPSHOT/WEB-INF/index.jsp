<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="pt">
    <%@include file="includes/head.jsp" %>
<body style="background-image: url('http://www.girassolpousada.com.br/wp-content/uploads/2016/04/copacabana-beach-at-rio-de-janeiro-brazil.jpg')">
    <%@include file="includes/navbar.jsp" %>
  
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
        <form method="GET" action="/room">
            <input type="text" class="form-control" placeholder='Copacabana'>
            <label> Quarto:</label>
            <select name="room_id" class="custom-select mb-2 mr-sm-2 mb-sm-0" id="inlineFormCustomSelect">
                <option selected> Escolha...</option>
                <c:forEach items="${rooms}" var="room">
                    <option value="${room.getID()}">${room.getName()}</option><br>
                </c:forEach>
            </select>
            <input type="submit" name="submit" value="Escolher"/>
        </form>
        </div>
        </div>
</div>

</body>
</html>
