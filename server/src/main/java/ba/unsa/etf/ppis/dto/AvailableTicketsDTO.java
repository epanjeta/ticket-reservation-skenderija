package ba.unsa.etf.ppis.dto;

import java.io.Serializable;

public class AvailableTicketsDTO implements Serializable {

    private Integer id;
    private TicketTypeDTO ticketTypeDTO;
    private Integer availableTickets;
    private Integer totalTickets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TicketTypeDTO getTicketTypeDTO() {
        return ticketTypeDTO;
    }

    public void setTicketTypeDTO(TicketTypeDTO ticketTypeDTO) {
        this.ticketTypeDTO = ticketTypeDTO;
    }

    public Integer getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(Integer availableTickets) {
        this.availableTickets = availableTickets;
    }

    public Integer getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(Integer totalTickets) {
        this.totalTickets = totalTickets;
    }
}
