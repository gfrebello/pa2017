package smarthome.database;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseDAO {

    private DataSource ds;

//------------------------------------------------------------------------------------------------------------
    public BaseDAO() {
        try {
            InitialContext cxt = new InitialContext();
            if (cxt == null) {
                System.out.println("[BaseDAO.constructor] InitialContext failed.");
            }else{     
                
                ds = (DataSource) cxt.lookup("java:comp/env/jdbc/domotica1");
            }
        } catch (Exception e) {
            System.out.println("[BaseDAO.constructor] Exception: " + e.getMessage());
        }
    }
//------------------------------------------------------------------------------------------------------------
    public Connection getConnection(){
        try{
            if(ds!=null){
                return ds.getConnection();
            }else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
//------------------------------------------------------------------------------------------------------------
}

