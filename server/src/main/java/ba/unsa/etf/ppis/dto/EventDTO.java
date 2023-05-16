package ba.unsa.etf.ppis.dto;

import ba.unsa.etf.ppis.constants.EventType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventDTO implements Serializable {
    private int id;

    private int seconds;
    private Long pictureId;
    private String title;
    private String date;
    private String description;
    private EventType type;
    private List<AvailableTicketsDTO> tickets = new ArrayList<>();
    private byte[] picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public List<AvailableTicketsDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<AvailableTicketsDTO> tickets) {
        this.tickets = tickets;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
