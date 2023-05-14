package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.dto.EventDTO;
import ba.unsa.etf.ppis.dto.NewEventDTO;
import ba.unsa.etf.ppis.entity.EventEntity;

import java.util.List;

public interface EventService {
    List<EventDTO> getAllEvents();

    EventDTO getEvent(int id);

    EventDTO saveEvent(NewEventDTO newEventDTO);

    EventEntity getEventEntity(int id);
}
