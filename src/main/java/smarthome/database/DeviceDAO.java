package smarthome.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeviceDAO extends BaseDAO {
    
        public boolean readDevice(DeviceDTO dto) {
        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(
               "SELECT * FROM dispositivos WHERE serialdispositivo=?;");
            pstmt.setInt(1, (new Integer(dto.getID())).intValue());
            ResultSet rst = pstmt.executeQuery();
            rst.next();
            dto.setName(rst.getString("nome"));
            dto.setRoomID(rst.getString("serialambientes"));
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
        
    public boolean createDevice(DeviceDTO dto) {
        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(
               "INSERT INTO dispositivos(nome,serialambientes) VALUES (?,?);");
            pstmt.setString(1, dto.getName());
            pstmt.setInt(2, (new Integer(dto.getRoomID())).intValue());
            pstmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }  
        return true;
    }
    
    public boolean updateDevice(DeviceDTO dto) {
        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(
               "UPDATE dispositivos SET nome=? WHERE serialdispositivo=?;");
            pstmt.setString(1, dto.getName());
            pstmt.setInt(2, (new Integer(dto.getID())).intValue());
            pstmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }      

    public boolean deleteDevice(DeviceDTO dto) {
        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(
               "DELETE FROM dispositivos WHERE serialdispositivo=?;");
            pstmt.setInt(1, (new Integer(dto.getID())).intValue());
            pstmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
