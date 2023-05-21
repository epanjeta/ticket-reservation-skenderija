package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.dto.TicketDTO;
import ba.unsa.etf.ppis.entity.*;
import ba.unsa.etf.ppis.mappers.TicketMapper;
import ba.unsa.etf.ppis.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    protected TicketRepository ticketRepository;

    @Autowired
    protected EventService eventService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected EventRepository eventRepository;

    @Autowired
    protected TicketTypeRepository ticketTypeRepository;

    @Autowired
    protected ImageService imageService;

    @Autowired
    protected AvailableTicketsRepository availableTicketsRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected TaskRepository taskRepository;

    @Autowired
    protected LocationRepository locationRepository;

    @Override
    public List<TicketDTO> saveReservations(TicketDTO reservations) {
        List<TicketDTO> ticketsDTO = new ArrayList<>();
        int amount = reservations.getAmount();
        EventEntity event = eventRepository.findById(reservations.getEventDTO().getId());
        TicketTypeEntity ticketType = ticketTypeRepository.getReferenceById(reservations.getType());
        AvailableTicketsEntity availableTicketsEntity = availableTicketsRepository.getReferenceById(reservations.getType());
        UserEntity user = userRepository.findById(reservations.getUserDTO()).get();

        for(int i = 0; i < amount; i++){
            if ((reservations.getStatus().equals("DOWNLOAD"))) {
                // Napraviti ticket
                TicketEntity ticketEntity = TicketMapper.toEntity(reservations, event, ticketType, user);
                TicketDTO ticketDTO = TicketMapper.toProjection(ticketRepository.save(ticketEntity), imageService.getImageWithId(event.getPicture().getId()), user, null);
                ticketsDTO.add(ticketDTO);
                // Smanjiti available za 1
                availableTicketsEntity.setAvailableTickets(availableTicketsEntity.getAvailableTickets() - 1);
            }
            else if ((reservations.getStatus().equals("PENDING"))) {
                // Napraviti ticket
                LocationEntity location = locationRepository.getReferenceById(reservations.getLocation().getId());
                TicketEntity ticketEntity = TicketMapper.toEntity(reservations, event, ticketType, user);
                TicketEntity tickedEntitySaved = ticketRepository.save(ticketEntity);
                TicketDTO ticketDTO = TicketMapper.toProjection(tickedEntitySaved, imageService.getImageWithId(event.getPicture().getId()), user, location);
                ticketsDTO.add(ticketDTO);
                // Napraviti task
                TaskEntity taskEntity = new TaskEntity();
                taskEntity.setLocation(location);
                taskEntity.setTicket(tickedEntitySaved);
                taskRepository.save(taskEntity);
                // Smanjiti available za 1
                availableTicketsEntity.setAvailableTickets(availableTicketsEntity.getAvailableTickets() - 1);
            }

        }
        availableTicketsRepository.save(availableTicketsEntity);
        return ticketsDTO;
    }
}
