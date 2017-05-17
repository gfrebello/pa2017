<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="pt">
    <%@include file="includes/head.jsp" %>
    <body style="background-image: url('images/copacabana.jpg'); background-attachment: fixed">
        <%@include file="includes/navbar.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-sm-12 main-header">
                    <h1> Copacabana </h1>
                    <span>Rio de Janeiro, 19 de Maio de 2017</span>
                </div>
                <div class="row">
                    <c:forEach items="${rooms}" var="room">
                        <div class="col-sm-10 col-sm-offset-1 main-box" id="room_${room.getID()}" style="background-image: url('images/${room.getID()}.jpg')">
                            <a href="/home/room?room_id=${room.getID()}" style="display: block; width: 100%; height: 100%">
                                <h2>${room.getName()}</h2>
                                <p>${room.getDevices().size()} dispositivo(s)</p>
                            </a>
                        </div>
                        <br>
                    </c:forEach>
                    <div class="col-sm-10 col-sm-offset-1 add-box">
                            
                                <h2>Novo ambiente</h2>
                                <label>Nome do ambiente:</label>
                                <input type="text" id="room_name" placeholder="Novo quarto">
                                <button type="text" id="createRoom">Criar</button>                      
                    </div>
                    <br>
                    <div class="col-sm-10 col-sm-offset-1 delete-box">
                            
                                <h2>Excluir ambiente</h2>
                                <label>Nome do ambiente:</label>
                                <select id="room_id">
                                    <c:forEach items="${rooms}" var="room">
                                        <option value="${room.getID()}">${room.getName()}</option>
                                    </c:forEach>}
                                </select>
                                <button id="deleteRoom">Excluir</button>                      
                    </div>
                    <br>                    
                </div>
            </div>
        </div>
    </body>
    
    <script>
        $(document).ready(function() {
            $('#createRoom').click(function(){
                var room_name = $('#room_name').val();
                $.post('home',{
                       room_name: room_name,
                       action: 'create'
                },
                function(data) {
                     location.reload();
                });
            });
            
             $('#deleteRoom').click(function(){
                var room_id = $('#room_id').val();
                $.post('home',{
                       room_id: room_id,
                       action: 'delete'
                },
                function(data) {
                     $('#room_'+room_id).hide();
                });
            });
            
        });    
    </script>
</html>
