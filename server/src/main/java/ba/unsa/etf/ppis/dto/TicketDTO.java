package ba.unsa.etf.ppis.dto;

import java.io.Serializable;

public class TicketDTO implements Serializable {

    private int id;

    private EventDTO eventDTO;

    private String status;

    private int type;

    private int userId;

    private int amount;

    private LocationDTO location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }

    public void setEventDTO(EventDTO eventDTO) {
        this.eventDTO = eventDTO;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserDTO() {
        return userId;
    }

    public void setUserDTO(int userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }
}
