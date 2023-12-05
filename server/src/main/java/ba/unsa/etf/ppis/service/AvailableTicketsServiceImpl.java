package ba.unsa.etf.ppis.service;


import ba.unsa.etf.ppis.constants.ApiResponseMessages;
import ba.unsa.etf.ppis.dto.AvailableTicketsDTO;
import ba.unsa.etf.ppis.entity.AvailableTicketsEntity;
import ba.unsa.etf.ppis.entity.EventEntity;
import ba.unsa.etf.ppis.entity.TicketTypeEntity;
import ba.unsa.etf.ppis.exception.NoAccessException;
import ba.unsa.etf.ppis.mappers.AvailableTicketsMapper;
import ba.unsa.etf.ppis.mappers.TicketTypeMapper;
import ba.unsa.etf.ppis.repository.AvailableTicketsRepository;
import ba.unsa.etf.ppis.repository.EventRepository;
import ba.unsa.etf.ppis.repository.TicketTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AvailableTicketsServiceImpl implements AvailableTicketsService{

    @Autowired protected AvailableTicketsRepository availableTicketsRepository;

    @Autowired protected EventRepository eventRepository;

    @Autowired protected TicketTypeRepository ticketTypeRepository;

    @Autowired protected AuthService authService;

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

    @Override
    public AvailableTicketsDTO getTicketsForEventByType(Integer ticketTypeId, String authorizationHeader) {

            if(authorizationHeader == null || authorizationHeader.isEmpty())
                throw new NoAccessException(ApiResponseMessages.NO_ACCESS);

            if(!authService.isUserRole(authorizationHeader))
                throw new NoAccessException(ApiResponseMessages.NO_ACCESS);

            List<AvailableTicketsEntity> availableTicketsDTOS
                    = availableTicketsRepository.findAll();
            AvailableTicketsEntity availableTicketsEntity = availableTicketsDTOS.stream().filter(ticket -> {
                return Objects.equals(ticket.getTicketType().getId(), ticketTypeId);
            }).findAny().orElse(null);
            AvailableTicketsDTO projection = AvailableTicketsMapper.toProjection(availableTicketsEntity);
            TicketTypeEntity ticketTypeEntity = ticketTypeRepository.findById(ticketTypeId).get();
            projection.setTicketTypeDTO(TicketTypeMapper.toProjection(ticketTypeEntity));
            return projection;

    }


}
