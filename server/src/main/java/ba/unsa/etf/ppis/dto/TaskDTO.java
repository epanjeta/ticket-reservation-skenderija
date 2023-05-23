package ba.unsa.etf.ppis.dto;

import ba.unsa.etf.ppis.entity.LocationEntity;
import ba.unsa.etf.ppis.entity.TicketEntity;

import java.io.Serializable;

public class TaskDTO implements Serializable {

    private Integer id;
    private LocationDTO location;
    private TicketDTO ticket;

    public TaskDTO() {
    }

    public TaskDTO(Integer id, LocationDTO location, TicketDTO ticket) {
        this.id = id;
        this.location = location;
        this.ticket = ticket;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public TicketDTO getTicket() {
        return ticket;
    }

    public void setTicket(TicketDTO ticket) {
        this.ticket = ticket;
    }
}
