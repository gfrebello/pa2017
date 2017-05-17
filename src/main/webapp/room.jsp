<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="pt">
    <%@include file="includes/head.jsp" %>
    <body style="background-image: url(<c:url value="/images/${dto.getID()}.jpg" />); background-attachment: fixed">
        <%@include file="includes/navbar.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-sm-12 main-header">
                    <h1> Copacabana </h1>
                    <span> Rio de Janeiro, 19 de Maio de 2017</span>
                    <br>
                    <h2><input style="text-align: center" type='text' id='room_name' value="${dto.getName()}" /></h2>
                    <input type='hidden' id='room_id' value="${dto.getID()}" />
                </div>
                <div class="row">
                <c:forEach items="${dto.getDevices()}" var="device">
                    <div class="col-sm-5 col-sm-offset-1 device-box" id="device_${device.getID()}" style="background-image: url('')">       
                            <h2><input type='text' id='edit_device_name' value="${device.getName()}" /></h2>
                            <button id="editDevice">Salvar</button>
                            <button id="deleteDevice">Excluir</button>
                            <input type='hidden' id='device_id' value="${device.getID()}" />
                    </div>
                    <br>
                </c:forEach>
                    <div class="col-sm-10 col-sm-offset-1 add-device-box">
                        <h2>Novo dispositivo</h2>
                        <label>Nome do dispositivo:</label>
                        <input type="text" id="new_device_name" value="" placeholder="Novo dispositivo">
                        <button type="text" id="createDevice">Criar</button>                      
                    </div>
                    <br>
                </div>
            </div>
        </div>
    </body>
    
    <script>
        $(document).ready(function() {
            $('#room_name').prop('readonly',true);
            $('#room_name').click(function() {
                if($('#room_name').prop('readonly')===true)
                    $('#room_name').prop('readonly',false);
                else {
                    $('#room_name').prop('readonly',true);
                    var room_id = $('#room_id').val();
                    var new_name = $('#room_name').val();
                    $.post('room', {
                        room_id: room_id,
                        new_name: new_name,
                        action: 'update'
                        },
                        function(data) {
                            alert("Nome alterado com sucesso! :)");
                        }
                    );   
                }
            });
            
            $('#createDevice').click(function() {
                var room_id = $('#room_id').val();
                var device_name = $('#new_device_name').val();
                $.post('room',{
                        room_id: room_id,
                        device_name: device_name,
                        action: 'create-device'
                        },
                        function(data) {
                             location.reload();
                        }
                );
            }); 
            
            $('#editDevice').click(function() {
                if($('#edit_device_name').prop('readonly')===true)
                    $('#edit_device_name').prop('readonly',false);
                else {
                    $('#edit_device_name').prop('readonly',true);
                    var device_id = $('#device_id').val();
                    var new_name = $('#edit_device_name').val();
                    $.post('room', {
                        device_id: device_id,
                        new_name: new_name,
                        action: 'update-device'
                        },
                        function(data) {
                            alert("Nome do dispositivo alterado com sucesso! :)");
                        }
                    );   
                }
            });
            
                        
            $('#deleteDevice').click(function() {
                var device_name = $('#edit_device_name').first().val();
                var device_id = $('#device_id').first().val();
                if(confirm("Tem certeza que deseja exluir " + device_name + "?")) {
                    $.post('room', {
                        device_id: device_id,
                        action: 'delete-device'
                        },
                        function() {
                            location.reload();
                        }
                    );   
                }
            });
        
        });
    </script>
    
</html>

