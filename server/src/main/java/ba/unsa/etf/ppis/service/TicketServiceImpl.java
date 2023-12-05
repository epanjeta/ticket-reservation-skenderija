package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.constants.ApiResponseMessages;
import ba.unsa.etf.ppis.constants.TaskStatus;
import ba.unsa.etf.ppis.constants.TicketStatus;
import ba.unsa.etf.ppis.dto.TicketDTO;
import ba.unsa.etf.ppis.entity.*;
import ba.unsa.etf.ppis.exception.NoAccessException;
import ba.unsa.etf.ppis.mappers.TicketMapper;
import ba.unsa.etf.ppis.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    protected EmailService emailService;

    @Autowired
    protected AuthService authService;

    @Override
    public List<TicketDTO> saveReservations(TicketDTO reservations, String authorizationHeader) {

        if(authorizationHeader == null || authorizationHeader.isEmpty())
            throw new NoAccessException(ApiResponseMessages.NO_ACCESS);

        if(!authService.isUserRole(authorizationHeader))
            throw new NoAccessException(ApiResponseMessages.NO_ACCESS);

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
                taskEntity.setStatus(TaskStatus.IN_PROGRESS);
                taskRepository.save(taskEntity);
                UserEntity userEntity = userService.getAdminByLocation(location.getId());
                emailService.sendEmail("New task!", "New task has been created!", userEntity);
                // Smanjiti available za 1
                availableTicketsEntity.setAvailableTickets(availableTicketsEntity.getAvailableTickets() - 1);
            }

        }
        availableTicketsRepository.save(availableTicketsEntity);
        return ticketsDTO;
    }

    @Override
    public void changeTicketStatus(TicketEntity ticketEntity, TicketStatus ticketStatus) {
        ticketRepository.save(ticketEntity);
    }

    @Override
    public List<TicketDTO> getAllTicketsByUserId(Integer id, String authorizationHeader) {

        if(authorizationHeader == null || authorizationHeader.isEmpty())
            throw new NoAccessException(ApiResponseMessages.NO_ACCESS);

        if(!authService.isUserRole(authorizationHeader))
            throw new NoAccessException(ApiResponseMessages.NO_ACCESS);

        List<TicketEntity> ticketEntities = ticketRepository.findAll();
        List<TicketEntity> ticketEntitiesForUser = ticketEntities.stream().filter(t -> t.getUser().getId() == id).collect(Collectors.toList());
        List<TicketDTO> ticketsDTO = new ArrayList<>();
        for(int i=0; i<ticketEntitiesForUser.size(); i++){
            UserEntity user = userRepository.findById(ticketEntitiesForUser.get(i).getUser().getId()).get();
            ticketsDTO.add(TicketMapper.toProjection(ticketEntitiesForUser.get(i), imageService.getImageWithId(ticketEntitiesForUser.get(i).getEvent().getPicture().getId()), user, null));
        }
        return ticketsDTO;
    }
}
