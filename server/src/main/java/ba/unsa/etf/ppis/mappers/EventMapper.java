package ba.unsa.etf.ppis.mappers;

import ba.unsa.etf.ppis.dto.EventDTO;
import ba.unsa.etf.ppis.entity.EventEntity;
import ba.unsa.etf.ppis.entity.ImageEntity;
import ba.unsa.etf.ppis.util.ImageUtils;

import java.time.format.DateTimeFormatter;

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
}
