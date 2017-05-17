package smarthome.database;

import java.io.Serializable;

public class DeviceDTO implements Serializable {
    private String id;
    private String room_id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }
    
    public String getRoomID() {
        return room_id;
    }

    public void setRoomID(String room_id) {
        this.room_id = room_id;
    }
    
}
