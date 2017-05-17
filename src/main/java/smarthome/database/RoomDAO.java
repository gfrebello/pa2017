package smarthome.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class RoomDAO extends BaseDAO {
   
    public boolean createRoom(RoomDTO dto) {
        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(
               "INSERT INTO ambientes(nome) VALUES (?);");
            pstmt.setString(1, dto.getName());
            pstmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }  
        return true;
    }
    
    public boolean updateRoom(RoomDTO dto) {
        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(
               "UPDATE ambientes SET nome=? WHERE serialambientes=?;");
            pstmt.setString(1, dto.getName());
            pstmt.setInt(2, (new Integer(dto.getID())).intValue());
            ResultSet rst = pstmt.executeQuery();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }    
    
    public boolean findRoom(RoomDTO dto) {
        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(
               "SELECT * FROM ambientes WHERE serialambientes=?;");
            pstmt.setInt(1, (new Integer(dto.getID())).intValue());
            ResultSet rst = pstmt.executeQuery();
            rst.next();
            dto.setName(rst.getString("nome"));
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //Get all rooms and associated devices
    public boolean findAllRooms(List<RoomDTO> roomlist) {
        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(
               "SELECT * FROM ambientes;");
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()) {
                RoomDTO room = new RoomDTO();
                room.setID(rst.getString("serialambientes"));
                room.setName(rst.getString("nome"));
                findDevices(room);
                roomlist.add(room);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
       public boolean deleteRoom(RoomDTO dto) {
        try {
            Connection con = getConnection();
            PreparedStatement pstmt1 = con.prepareStatement(
               "DELETE FROM ambientes WHERE serialambientes=?;");
            PreparedStatement pstmt2 = con.prepareStatement(
               "DELETE FROM dispositivos WHERE serialambientes=?;");
            pstmt1.setInt(1, (new Integer(dto.getID())).intValue());
            pstmt2.setInt(1, (new Integer(dto.getID())).intValue());
            pstmt2.executeUpdate();
            pstmt1.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }  
        return true;
    }
    
    
    public boolean findDevices(RoomDTO dto) {
        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(
               "SELECT * FROM dispositivos WHERE serialambientes=?;");
            pstmt.setInt(1, (new Integer(dto.getID())).intValue());
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()) {
                DeviceDTO device = new DeviceDTO();
                device.setName(rst.getString("nome"));  
                device.setID(rst.getString("serialdispositivo"));
                device.setRoomID(dto.getID());   
                dto.addDevice(device);
            };
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
