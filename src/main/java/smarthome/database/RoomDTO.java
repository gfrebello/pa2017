package smarthome.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomDTO implements Serializable {
    private String id;
    private String name;
    private List<DeviceDTO> devices = new ArrayList<>();

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

    public List<DeviceDTO> getDevices() {
        return devices;
    }

    public void addDevice(DeviceDTO device) {
        devices.add(device);
    }
    
    public void resetDevices() {
        devices = new ArrayList<>();
    }
}
