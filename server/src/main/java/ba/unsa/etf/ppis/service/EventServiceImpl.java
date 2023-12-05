package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.constants.ApiResponseMessages;
import ba.unsa.etf.ppis.dto.DateDTO;
import ba.unsa.etf.ppis.dto.EventDTO;
import ba.unsa.etf.ppis.entity.EventEntity;
import ba.unsa.etf.ppis.entity.ImageEntity;
import ba.unsa.etf.ppis.entity.TicketTypeEntity;
import ba.unsa.etf.ppis.exception.NoAccessException;
import ba.unsa.etf.ppis.mappers.AvailableTicketsMapper;
import ba.unsa.etf.ppis.mappers.EventMapper;
import ba.unsa.etf.ppis.mappers.TicketTypeMapper;
import ba.unsa.etf.ppis.repository.AvailableTicketsRepository;
import ba.unsa.etf.ppis.repository.EventRepository;
import ba.unsa.etf.ppis.repository.TicketTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    @Autowired protected EventRepository eventRepository;
    @Autowired protected ImageService imageService;
    @Autowired protected AvailableTicketsService availableTicketsService;
    @Autowired protected AvailableTicketsRepository availableTicketsRepository;
    @Autowired protected TicketTypeRepository ticketTypeRepository;
    @Autowired protected AuthService authService;

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
    public EventDTO saveEvent(EventDTO newEventDTO, String authorizationHeader) {

        if(authorizationHeader == null || authorizationHeader.isEmpty())
            throw new NoAccessException(ApiResponseMessages.NO_ACCESS);

        if(!authService.isAdminRole(authorizationHeader))
            throw new NoAccessException(ApiResponseMessages.NO_ACCESS);

        ImageEntity image = imageService.getImageEntityWithId(newEventDTO.getPictureId());
        EventEntity event = eventRepository.save(EventMapper.toEntity(newEventDTO, image));

        for (var ticket : newEventDTO.getTickets()) {
            TicketTypeEntity ticketType = ticketTypeRepository.save(TicketTypeMapper.toEntity(ticket.getTicketTypeDTO()));
            availableTicketsRepository.save(AvailableTicketsMapper.toEntity(ticket, event, ticketType));
        }

        return getEvent(event.getId());
    }

    @Override
    public DateDTO checkIfAvailable(DateDTO date, String authorizationHeader) {

        if(authorizationHeader == null || authorizationHeader.isEmpty())
            throw new NoAccessException(ApiResponseMessages.NO_ACCESS);

        if(!authService.isAdminRole(authorizationHeader))
            throw new NoAccessException(ApiResponseMessages.NO_ACCESS);

        List<EventEntity> events = eventRepository.findAll();
        DateDTO isAvailable = new DateDTO(date.getDate(), true);

        for (int i=0; i<events.size(); i++){
            ZonedDateTime date1 = events.get(i).getDate();
            if(date1.getYear() == date.getDate().getYear()
            && date1.getMonthValue() == date.getDate().getMonthValue()
            && date1.getDayOfMonth() == date.getDate().getDayOfMonth()) isAvailable.setAvailable(false);
        }
        return isAvailable;
    }
}
