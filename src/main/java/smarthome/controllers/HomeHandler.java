package smarthome.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import smarthome.database.RoomDAO;
import smarthome.database.RoomDTO;

@WebServlet("/home")
public class HomeHandler extends HttpServlet {
    
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RoomDTO> rooms;
        rooms = findAllRooms();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

   @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        String action = request.getParameter("action");
        RoomDTO dto = new RoomDTO();
        switch(action){
            case "create":
                dto.setName(request.getParameter("room_name"));
                if(new RoomDAO().createRoom(dto))
                    response.getWriter().write("sucesso");
                else response.getWriter().write("ERRO");  
                break;
            case "delete":
                dto.setID(request.getParameter("room_id"));
                if(new RoomDAO().deleteRoom(dto))
                    response.getWriter().write("sucesso");
                else response.getWriter().write("ERRO"); 
                break;
        }
    }

    
    public List<RoomDTO> findAllRooms() {
        List<RoomDTO> roomlist = new ArrayList<>();
        if(new RoomDAO().findAllRooms(roomlist))
            //Query feita com sucesso!
            return roomlist;
        //Erro!
        return null;
    }
}
    