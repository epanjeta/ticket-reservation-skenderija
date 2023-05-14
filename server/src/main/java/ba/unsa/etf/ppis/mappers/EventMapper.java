package ba.unsa.etf.ppis.mappers;

import ba.unsa.etf.ppis.constants.EventType;
import ba.unsa.etf.ppis.dto.EventDTO;
import ba.unsa.etf.ppis.dto.NewEventDTO;
import ba.unsa.etf.ppis.entity.EventEntity;
import ba.unsa.etf.ppis.entity.ImageEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class EventMapper {

    private EventMapper() {
    }

    public static EventDTO toProjection(EventEntity entity, byte[] image) {
        if (entity == null) {
            return null;
        }
        EventDTO projection = new EventDTO();
        projection.setId(entity.getId());
        projection.setTitle(entity.getTitle());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        String formattedString = entity.getDate().format(formatter);
        projection.setDate(formattedString);
        projection.setDescription(entity.getDescription());
        projection.setType(entity.getType());
        projection.setPicture(image);
        return projection;
    }

    public static EventEntity toEntity(NewEventDTO newEventDTO, ImageEntity image){
        if (newEventDTO == null){
            return null;
        }
        EventEntity entity = new EventEntity();
        entity.setTitle(newEventDTO.getTitle());
        entity.setDescription(newEventDTO.getDescription());
        LocalDate date = LocalDate.parse(newEventDTO.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDateTime dateTime = date.atStartOfDay();
        dateTime = dateTime.plusSeconds(newEventDTO.getSeconds());

        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.of("Europe/Paris"));

        entity.setDate(ZonedDateTime.from(zonedDateTime));
        entity.setPicture(image);
        if(Objects.equals(newEventDTO.getType(), "Koncert"))
            entity.setType(EventType.KONCERT);
        if(Objects.equals(newEventDTO.getType(), "Sajam"))
            entity.setType(EventType.SAJAM);
        if(Objects.equals(newEventDTO.getType(), "Seminar"))
            entity.setType(EventType.SEMINAR);
        return entity;
    }
}
