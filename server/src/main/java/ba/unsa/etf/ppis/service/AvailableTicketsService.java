package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.dto.AvailableTicketsDTO;
import ba.unsa.etf.ppis.entity.EventEntity;

import java.util.List;

public interface AvailableTicketsService {

    List<AvailableTicketsDTO> getAllTicketsForEvent(EventEntity event);

    AvailableTicketsDTO getTicketsForEventByType(Integer ticketTypeId, String authorizationHeader);

}
