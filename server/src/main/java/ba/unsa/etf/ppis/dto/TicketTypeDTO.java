package ba.unsa.etf.ppis.dto;

import java.io.Serializable;

public class TicketTypeDTO implements Serializable {

    private int id;
    private String ticketType;
    private Integer ticketPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
