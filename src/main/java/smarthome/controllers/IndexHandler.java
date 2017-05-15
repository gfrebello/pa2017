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

@WebServlet("/")
public class IndexHandler extends HttpServlet {
    
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RoomDTO> rooms;
        rooms = findAllRooms();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
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
