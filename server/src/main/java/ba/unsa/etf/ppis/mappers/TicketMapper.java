package ba.unsa.etf.ppis.mappers;

import ba.unsa.etf.ppis.constants.TicketStatus;
import ba.unsa.etf.ppis.dto.LocationDTO;
import ba.unsa.etf.ppis.dto.TicketDTO;
import ba.unsa.etf.ppis.entity.*;

public class TicketMapper {

    private TicketMapper(){

    }

    public static TicketEntity toEntity(TicketDTO projection, EventEntity event, TicketTypeEntity ticketType, UserEntity user){
        if (projection == null){
            return null;
        }
        TicketEntity entity = new TicketEntity();
        //entity.setEvent(EventMapper.toEntity(projection.getEventDTO(), image));
        //entity.setId(projection.getId());
        entity.setEvent(event);
        entity.setStatus(TicketStatus.valueOf(projection.getStatus()));
        entity.setType(ticketType);
        if(projection.getLocation() != null || projection.getLocation().getId() != 0){
            entity.setLocation(LocationMapper.toEntity(projection.getLocation()));
        }else {
            entity.setLocation(null);
        }
        //entity.setUser(UserMapper.mapToEntity(projection.getUserDTO(), null));
        entity.setUser(user);
        return entity;
    }

    public static TicketDTO toProjection(TicketEntity entity, byte[] image, UserEntity user, LocationEntity location){
        if (entity == null){
            return null;
        }
        TicketDTO projection = new TicketDTO();
        projection.setId(entity.getId());
        projection.setEventDTO(EventMapper.toProjection(entity.getEvent(), image));
        projection.setAmount(1);
        projection.setStatus(entity.getStatus().name());
        projection.setType(entity.getType().getId());
        //projection.setUserDTO(UserMapper.mapToProjection(entity.getUser()));
        projection.setUserDTO(user.getId());
        if(location == null || location.getId() == 0)
            projection.setLocation(null);
        else
            projection.setLocation(new LocationDTO(location.getId(), location.getName()));
        return projection;
    }
}


