package ba.unsa.etf.ppis.mappers;

import ba.unsa.etf.ppis.dto.AvailableTicketsDTO;
import ba.unsa.etf.ppis.entity.AvailableTicketsEntity;
import ba.unsa.etf.ppis.entity.EventEntity;

public class AvailableTicketsMapper {

    public AvailableTicketsMapper() {
    }

    public static AvailableTicketsDTO toProjection(AvailableTicketsEntity entity){
        if(entity == null){
            return null;
        }
        AvailableTicketsDTO availableTicketsDTO = new AvailableTicketsDTO();

        availableTicketsDTO.setEventId(entity.getEvent().getId());
        availableTicketsDTO.setParterTickets(entity.getParterTickets());
        availableTicketsDTO.setBackstageTickets(entity.getBackstageTickets());
        availableTicketsDTO.setVipTickets(entity.getVipTickets());
        return availableTicketsDTO;
    }

    public static AvailableTicketsEntity toEntity(AvailableTicketsDTO projection, EventEntity event){
        if(projection == null){
            return null;
        }
        AvailableTicketsEntity availableTicketsEntity = new AvailableTicketsEntity();
        availableTicketsEntity.setEvent(event);
        availableTicketsEntity.setBackstageTickets(projection.getBackstageTickets());
        availableTicketsEntity.setParterTickets(projection.getParterTickets());
        availableTicketsEntity.setVipTickets(projection.getVipTickets());
        return availableTicketsEntity;
    }
}
