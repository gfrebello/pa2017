package smarthome.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class RoomDAO extends BaseDAO {
    
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
                roomlist.add(room);
            }
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
