package ba.unsa.etf.ppis.dto;

import java.time.LocalDate;
import java.util.Date;

public class DateDTO {

    private LocalDate date;
    private Boolean available;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public DateDTO() {
    }

    public DateDTO(LocalDate date) {
        this.date = date;
    }

    public DateDTO(LocalDate date, Boolean available) {
        this.date = date;
        this.available = available;
    }
}
