package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.dto.EventDTO;
import ba.unsa.etf.ppis.entity.EventEntity;
import ba.unsa.etf.ppis.entity.ImageEntity;
import ba.unsa.etf.ppis.entity.TicketTypeEntity;
import ba.unsa.etf.ppis.mappers.AvailableTicketsMapper;
import ba.unsa.etf.ppis.mappers.EventMapper;
import ba.unsa.etf.ppis.mappers.TicketTypeMapper;
import ba.unsa.etf.ppis.repository.AvailableTicketsRepository;
import ba.unsa.etf.ppis.repository.EventRepository;
import ba.unsa.etf.ppis.repository.TicketTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    @Autowired protected EventRepository eventRepository;
    @Autowired protected ImageService imageService;
    @Autowired protected AvailableTicketsService availableTicketsService;
    @Autowired protected AvailableTicketsRepository availableTicketsRepository;
    @Autowired protected TicketTypeRepository ticketTypeRepository;

    @Override
    public List<EventDTO> getAllEvents() {
        List<EventEntity> events = eventRepository.findAll();
        List<EventDTO> projections = new ArrayList<>();
        for (var event: events) {
            projections.add(EventMapper.toProjection(event, imageService.getImageWithId(event.getPicture().getId())));
        }
        return projections;
    }

    @Override
    public EventDTO getEvent(int id) {
        EventEntity event = eventRepository.findById(id);
        EventDTO projection = EventMapper.toProjection(event, imageService.getImageWithId(event.getPicture().getId()));
        projection.setTickets(availableTicketsService.getAllTicketsForEvent(event));
        return projection;
    }

    @Override
    public EventDTO saveEvent(EventDTO newEventDTO) {
        ImageEntity image = imageService.getImageEntityWithId(newEventDTO.getPictureId());
        EventEntity event = eventRepository.save(EventMapper.toEntity(newEventDTO, image));

        for (var ticket : newEventDTO.getTickets()) {
            TicketTypeEntity ticketType = ticketTypeRepository.save(TicketTypeMapper.toEntity(ticket.getTicketTypeDTO()));
            availableTicketsRepository.save(AvailableTicketsMapper.toEntity(ticket, event, ticketType));
        }

        return getEvent(event.getId());
    }


}
