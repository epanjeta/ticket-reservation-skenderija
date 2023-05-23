package ba.unsa.etf.ppis.entity;

import ba.unsa.etf.ppis.constants.DatabaseConstants;
import ba.unsa.etf.ppis.constants.TicketStatus;
import ba.unsa.etf.ppis.constants.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(schema = DatabaseConstants.DATABASE_SCHEMA, name = "ticket" )
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TicketStatus status;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "event", referencedColumnName = "id")
    private EventEntity event;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "type", referencedColumnName = "id")
    private TicketTypeEntity type;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "location", referencedColumnName = "id")
    private LocationEntity location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public TicketTypeEntity getType() {
        return type;
    }

    public void setType(TicketTypeEntity type) {
        this.type = type;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }
}
