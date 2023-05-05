package ba.unsa.etf.ppis.entity;


import ba.unsa.etf.ppis.constants.DatabaseConstants;
import ba.unsa.etf.ppis.constants.EventType;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(schema = DatabaseConstants.DATABASE_SCHEMA, name = "event" )
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EventType type;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date", nullable = false)
    private ZonedDateTime date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "picture", referencedColumnName = "id")
    private ImageEntity picture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public ImageEntity getPicture() {
        return picture;
    }

    public void setPicture(ImageEntity picture) {
        this.picture = picture;
    }
}
