package smarthome.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import smarthome.database.DeviceDAO;
import smarthome.database.DeviceDTO;
import smarthome.database.RoomDAO;
import smarthome.database.RoomDTO;


@WebServlet(urlPatterns = {"/home/room"})
public class RoomHandler extends HttpServlet {
    
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoomDTO dto;
        dto = getRoom(request);
        dto = findDevices(dto);
        request.setAttribute("dto", dto);
        request.getRequestDispatcher("/room.jsp").forward(request, response);
    }
        
   @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        String action = request.getParameter("action");
        RoomDTO dto = new RoomDTO();
        DeviceDTO d_dto = new DeviceDTO();
        switch(action){
            case "update":
                dto.setID(request.getParameter("room_id"));
                dto.setName(request.getParameter("new_name"));
                if(new RoomDAO().updateRoom(dto))
                    response.getWriter().write("Sucesso");
                else response.getWriter().write("ERRO");     
                break;
            case "create-device":
                d_dto.setName(request.getParameter("device_name"));
                d_dto.setRoomID(request.getParameter("room_id"));
                if(new DeviceDAO().createDevice(d_dto)) {
                    dto.addDevice(d_dto);
                    response.getWriter().write("Suceso");
                }
                else response.getWriter().write("ERRO");
                break;
            case "update-device":
                d_dto.setID(request.getParameter("device_id"));
                d_dto.setName(request.getParameter("new_name"));
                if(new DeviceDAO().updateDevice(d_dto))
                    response.getWriter().write("Sucesso");
                else response.getWriter().write("ERRO");     
                break;
            case "delete-device":
                d_dto.setID(request.getParameter("device_id"));
                if(new DeviceDAO().deleteDevice(d_dto)) {
                    dto.resetDevices();
                    response.getWriter().write("Sucesso");
                }                 
                else response.getWriter().write("ERRO");     
                break;                
        }
    }    
    
    
    public RoomDTO getRoom (HttpServletRequest request) {
        RoomDTO dto = new RoomDTO();
        dto.setID(request.getParameter("room_id"));
        if(new RoomDAO().findRoom(dto))
            //Query feita com sucesso!
            return dto;
        //Erro!
        return null;
    }
    
    
    public RoomDTO updateRoom (HttpServletRequest request) {
        RoomDTO dto = new RoomDTO();
        dto.setID(request.getParameter("room_id"));
        dto.setName(request.getParameter("newname"));
        if(new RoomDAO().updateRoom(dto))
            //Dispositivos encontrados!
            return dto;
        //Erro!
        return null;
    }    
  
    public RoomDTO deleteRoom (HttpServletRequest request) {
        RoomDTO dto = new RoomDTO();
        dto.setID(request.getParameter("room_id"));
        if(new RoomDAO().deleteRoom(dto))
            //Dispositivos encontrados!
            return dto;
        //Erro!
        return null;
    }    
    
    
    public RoomDTO findDevices (RoomDTO dto) {
        if(new RoomDAO().findDevices(dto))
            //Dispositivos encontrados!
            return dto;
        //Erro!
        return null;
    }    
    
}
