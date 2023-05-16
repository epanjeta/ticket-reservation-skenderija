package ba.unsa.etf.ppis.mappers;

import ba.unsa.etf.ppis.dto.AvailableTicketsDTO;
import ba.unsa.etf.ppis.entity.AvailableTicketsEntity;
import ba.unsa.etf.ppis.entity.EventEntity;
import ba.unsa.etf.ppis.entity.TicketTypeEntity;

public class AvailableTicketsMapper {

    public AvailableTicketsMapper() {
    }

    public static AvailableTicketsDTO toProjection(AvailableTicketsEntity entity) {
        if (entity == null) {
            return null;
        }
        AvailableTicketsDTO availableTicketsDTO = new AvailableTicketsDTO();
        availableTicketsDTO.setId(entity.getId());
        availableTicketsDTO.setAvailableTickets(entity.getAvailableTickets());
        availableTicketsDTO.setTotalTickets(entity.getTotalTickets());
        return availableTicketsDTO;
    }

    public static AvailableTicketsEntity toEntity(AvailableTicketsDTO projection, EventEntity event, TicketTypeEntity ticketType) {
        if (projection == null) {
            return null;
        }
        AvailableTicketsEntity availableTicketsEntity = new AvailableTicketsEntity();
        availableTicketsEntity.setEvent(event);
        availableTicketsEntity.setAvailableTickets(projection.getAvailableTickets());
        availableTicketsEntity.setTotalTickets(projection.getTotalTickets());
        availableTicketsEntity.setTicketType(ticketType);
        return availableTicketsEntity;
    }
}
