package ba.unsa.etf.ppis.mappers;

import ba.unsa.etf.ppis.dto.EventDTO;
import ba.unsa.etf.ppis.dto.TicketTypeDTO;
import ba.unsa.etf.ppis.entity.EventEntity;
import ba.unsa.etf.ppis.entity.TicketTypeEntity;

import java.time.format.DateTimeFormatter;

public class TicketTypeMapper {

    public static TicketTypeDTO toProjection(TicketTypeEntity entity) {
        if (entity == null) {
            return null;
        }
        TicketTypeDTO projection = new TicketTypeDTO();
        projection.setId(entity.getId());
        projection.setTicketType(entity.getTicketType());
        projection.setTicketPrice(entity.getTicketPrice());
        return projection;
    }

    public static TicketTypeEntity toEntity(TicketTypeDTO ticketTypeDTO) {
        TicketTypeEntity ticketType = new TicketTypeEntity();
        ticketType.setTicketPrice(ticketTypeDTO.getTicketPrice());
        ticketType.setTicketType(ticketTypeDTO.getTicketType());
        return ticketType;
    }
}
