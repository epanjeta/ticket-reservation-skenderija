package ba.unsa.etf.ppis.entity;


import ba.unsa.etf.ppis.constants.DatabaseConstants;
import jakarta.persistence.*;

@Entity
@Table(name = "availabletickets" )
public class AvailableTicketsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event", referencedColumnName = "id")
    private EventEntity event;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_type", referencedColumnName = "id")
    private TicketTypeEntity ticketType;

    @Column(name = "available_tickets", nullable = false)
    private Integer availableTickets;

    @Column(name = "total_tickets", nullable = false)
    private Integer totalTickets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public TicketTypeEntity getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketTypeEntity ticketType) {
        this.ticketType = ticketType;
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
