package ba.unsa.etf.ppis.mappers;

import ba.unsa.etf.ppis.constants.TaskStatus;
import ba.unsa.etf.ppis.dto.TaskDTO;
import ba.unsa.etf.ppis.dto.TicketDTO;
import ba.unsa.etf.ppis.entity.*;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    public TaskMapper() {
    }

    public static TaskEntity toEntity(TaskDTO projection, TicketEntity ticketEntity){
        if (projection == null){
            return null;
        }
        TaskEntity entity = new TaskEntity();
        entity.setId(projection.getId());
        entity.setLocation(LocationMapper.toEntity(projection.getLocation()));
        entity.setTicket(ticketEntity);
        entity.setStatus(TaskStatus.IN_PROGRESS);

        return entity;
    }

    public static TaskDTO toProjection(TaskEntity entity, TicketDTO ticketDTO){
        if (entity == null){
            return null;
        }
        TaskDTO projection = new TaskDTO();
        projection.setId(entity.getId());
        projection.setLocation(LocationMapper.toProjection(entity.getLocation()));
        projection.setTicket(ticketDTO);

        return projection;
    }

    public static List<TaskDTO> toProjections(List<TaskEntity> entities, List<TicketDTO> ticketDTOS){
        if (entities == null){
            return null;
        }

        List<TaskDTO> projections = new ArrayList<>();

        for (int i=0; i<entities.size(); i++){
            projections.add(TaskMapper.toProjection(entities.get(i), ticketDTOS.get(i)));
        }

        return projections;
    }
}
