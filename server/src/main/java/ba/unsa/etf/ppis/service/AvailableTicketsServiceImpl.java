package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.constants.ApiResponseMessages;
import ba.unsa.etf.ppis.dto.AvailableTicketsDTO;
import ba.unsa.etf.ppis.entity.AvailableTicketsEntity;
import ba.unsa.etf.ppis.entity.EventEntity;
import ba.unsa.etf.ppis.mappers.AvailableTicketsMapper;
import ba.unsa.etf.ppis.mappers.EventMapper;
import ba.unsa.etf.ppis.repository.AvailableTicketsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class AvailableTicketsServiceImpl implements AvailableTicketsService{

    @Autowired
    protected AvailableTicketsRepository availableTicketsRepository;

    @Autowired
    protected EventService eventService;

    @Override
    public AvailableTicketsDTO saveAvailableTicketsForEvent(AvailableTicketsDTO availableTicketsDTO) {
        EventEntity event = eventService.getEventEntity(availableTicketsDTO.getEventId());
        if(event == null){
            throw new EntityNotFoundException(ApiResponseMessages.EVENT_NOT_FOUND);
        }

        AvailableTicketsEntity availableTicketsEntity = availableTicketsRepository.findAvailableTicketsEntitiesByEvent(event);
        if(availableTicketsEntity != null){
            availableTicketsEntity.setBackstageTickets(availableTicketsDTO.getBackstageTickets());
            availableTicketsEntity.setVipTickets(availableTicketsDTO.getVipTickets());
            availableTicketsEntity.setParterTickets(availableTicketsDTO.getParterTickets());
            return AvailableTicketsMapper.toProjection(availableTicketsRepository.save(availableTicketsEntity));
        }

        return AvailableTicketsMapper.toProjection(availableTicketsRepository.save(AvailableTicketsMapper.toEntity(availableTicketsDTO, event)));

    }
}
