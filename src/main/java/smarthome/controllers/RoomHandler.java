package smarthome.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import smarthome.database.RoomDAO;
import smarthome.database.RoomDTO;


@WebServlet(urlPatterns = {"/room"})
public class RoomHandler extends HttpServlet {
    
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoomDTO dto;
        dto = SearchRoom(request);
        request.setAttribute("dto", dto);
        dto = findDevices(dto);
        request.setAttribute("dto", dto);
        request.getRequestDispatcher("/WEB-INF/room.jsp").forward(request, response);
    }
        
    
    public RoomDTO SearchRoom (HttpServletRequest request) {
        RoomDTO dto = new RoomDTO();
        dto.setID(request.getParameter("room_id"));
        if(new RoomDAO().findRoom(dto))
            //Query feita com sucesso!
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
