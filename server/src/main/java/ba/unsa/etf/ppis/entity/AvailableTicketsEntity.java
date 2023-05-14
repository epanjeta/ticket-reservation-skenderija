package ba.unsa.etf.ppis.entity;


import ba.unsa.etf.ppis.constants.DatabaseConstants;
import jakarta.persistence.*;

@Entity
@Table(schema = DatabaseConstants.DATABASE_SCHEMA, name = "availabletickets" )
public class AvailableTicketsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event", referencedColumnName = "id")
    private EventEntity event;

    @Column(name = "parter")
    private Integer parterTickets;

    @Column(name = "vip")
    private Integer vipTickets;

    @Column(name = "backstage")
    private Integer backstageTickets;

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

    public Integer getParterTickets() {
        return parterTickets;
    }

    public void setParterTickets(Integer parterTickets) {
        this.parterTickets = parterTickets;
    }

    public Integer getVipTickets() {
        return vipTickets;
    }

    public void setVipTickets(Integer vipTickets) {
        this.vipTickets = vipTickets;
    }

    public Integer getBackstageTickets() {
        return backstageTickets;
    }

    public void setBackstageTickets(Integer backstageTickets) {
        this.backstageTickets = backstageTickets;
    }
}
