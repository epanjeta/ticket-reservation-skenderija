package ba.unsa.etf.ppis.service;


import ba.unsa.etf.ppis.dto.AvailableTicketsDTO;
import ba.unsa.etf.ppis.entity.AvailableTicketsEntity;
import ba.unsa.etf.ppis.entity.EventEntity;
import ba.unsa.etf.ppis.mappers.AvailableTicketsMapper;
import ba.unsa.etf.ppis.mappers.TicketTypeMapper;
import ba.unsa.etf.ppis.repository.AvailableTicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailableTicketsServiceImpl implements AvailableTicketsService{

    @Autowired
    protected AvailableTicketsRepository availableTicketsRepository;


    @Override
    public List<AvailableTicketsDTO> getAllTicketsForEvent(EventEntity event) {
        List<AvailableTicketsEntity> availableTicketsDTOS
                = availableTicketsRepository.findAvailableTicketsEntitiesByEvent(event);
        return availableTicketsDTOS.stream().map(entity -> {
            AvailableTicketsDTO projection = AvailableTicketsMapper.toProjection(entity);
            projection.setTicketTypeDTO(TicketTypeMapper.toProjection(entity.getTicketType()));
            return projection;
        }).collect(Collectors.toList());
    }


}
