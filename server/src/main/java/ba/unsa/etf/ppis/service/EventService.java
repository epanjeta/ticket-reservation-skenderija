package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.dto.EventDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> getAllEvents();

    EventDTO getEvent(int id);

    EventDTO saveEvent(EventDTO newEventDTO);
}
