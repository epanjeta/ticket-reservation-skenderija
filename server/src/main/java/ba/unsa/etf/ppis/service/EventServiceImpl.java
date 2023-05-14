package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.dto.EventDTO;
import ba.unsa.etf.ppis.dto.NewEventDTO;
import ba.unsa.etf.ppis.entity.EventEntity;
import ba.unsa.etf.ppis.entity.ImageEntity;
import ba.unsa.etf.ppis.mappers.EventMapper;
import ba.unsa.etf.ppis.repository.EventRepository;
import ba.unsa.etf.ppis.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    @Autowired protected EventRepository eventRepository;
    @Autowired protected ImageService imageService;

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
        return EventMapper.toProjection(event, imageService.getImageWithId(event.getPicture().getId()));
    }

    @Override
    public EventDTO saveEvent(NewEventDTO newEventDTO) {
        ImageEntity image = imageService.getImageEntityWithId(newEventDTO.getImageId());
        return EventMapper.toProjection(eventRepository.save(EventMapper.toEntity(newEventDTO, image)), imageService.getImageWithId(image.getId()));
    }
}
