package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.constants.TicketStatus;
import ba.unsa.etf.ppis.dto.EventDTO;
import ba.unsa.etf.ppis.dto.TaskDTO;
import ba.unsa.etf.ppis.dto.TicketDTO;
import ba.unsa.etf.ppis.dto.UserDTO;
import ba.unsa.etf.ppis.entity.*;
import ba.unsa.etf.ppis.mappers.TaskMapper;
import ba.unsa.etf.ppis.mappers.TicketMapper;
import ba.unsa.etf.ppis.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    protected EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

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
    protected TicketRepository ticketRepository;


    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        System.out.println(taskDTO.getId());
        TicketDTO reservations = taskDTO.getTicket();
        EventEntity event = eventRepository.findById(reservations.getEventDTO().getId());
        TicketTypeEntity ticketType = ticketTypeRepository.getReferenceById(reservations.getType());
        AvailableTicketsEntity availableTicketsEntity = availableTicketsRepository.getReferenceById(reservations.getType());
        UserEntity user = userRepository.findById(reservations.getUserDTO()).get();
        TicketEntity ticketEntity = TicketMapper.toEntity(reservations,event,ticketType,user);

        UserEntity userEntity = userService.getAdminByLocation(taskDTO.getLocation().getId());
        //emailService.sendEmail("New task!", "New task has been created!", userEntity);
        taskRepository.save(TaskMapper.toEntity(taskDTO, ticketEntity));
        return taskDTO;
    }

    @Override
    public List<TaskDTO> getTaskByLocationId(Integer locationId) {

        List<TaskEntity> taskEntityList = taskRepository.findAll();
        List<TaskEntity> taskEntities = taskEntityList.stream().filter(t -> t.getLocation().getId() == locationId).collect(Collectors.toList());


        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for(int i=0; i<taskEntities.size(); i++){
            TicketEntity reservations = taskEntities.get(i).getTicket();
            UserEntity user = userRepository.findById(reservations.getUser().getId()).get();
            TicketDTO ticketDTO = TicketMapper.toProjection(reservations, null, user, reservations.getLocation());
            ticketDTOs.add(ticketDTO);
        }

        return TaskMapper.toProjections(taskEntities, ticketDTOs);
    }

    @Transactional
    @Override
    public TaskDTO finishTask(TaskDTO taskDTO) {
        TicketDTO reservations = taskDTO.getTicket();
        EventEntity event = eventRepository.findById(reservations.getEventDTO().getId());
        TicketTypeEntity ticketType = ticketTypeRepository.getReferenceById(reservations.getType());
        UserEntity user = userRepository.findById(reservations.getUserDTO()).get();
        reservations.setStatus(TicketStatus.READY.name());
        TicketEntity ticketEntity = TicketMapper.toEntity(reservations,event,ticketType,user);
        ticketEntity.setId(reservations.getId());
        taskDTO.setLocation(null);

        //emailService.sendEmail("Ticket is ready!", "Your ticket is ready and waiting for you on location " + taskDTO.getLocation().getName(), user);
        ticketService.changeTicketStatus(ticketEntity, TicketStatus.valueOf("READY"));
        taskRepository.delete(TaskMapper.toEntity(taskDTO, ticketEntity));

        return taskDTO;
    }


}
