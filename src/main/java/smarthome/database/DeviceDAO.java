package smarthome.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeviceDAO extends BaseDAO {
    
        public boolean doRead(DeviceDTO dto) {
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
}
