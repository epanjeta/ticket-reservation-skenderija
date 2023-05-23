package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.constants.TaskStatus;
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
import java.util.Objects;
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
        TicketDTO reservations = taskDTO.getTicket();
        EventEntity event = eventRepository.findById(reservations.getEventDTO().getId());
        TicketTypeEntity ticketType = ticketTypeRepository.getReferenceById(reservations.getType());
        UserEntity user = userRepository.findById(reservations.getUserDTO()).get();
        TicketEntity ticketEntity = TicketMapper.toEntity(reservations,event,ticketType,user);

        taskRepository.save(TaskMapper.toEntity(taskDTO, ticketEntity));
        return taskDTO;
    }

    @Override
    public List<TaskDTO> getTaskByLocationId(Integer locationId) {

        List<TaskEntity> taskEntityList = taskRepository.findAll();
        List<TaskEntity> taskEntities = taskEntityList.stream()
                .filter(t -> TaskStatus.IN_PROGRESS.equals(t.getStatus()))
                .filter(t -> Objects.equals(t.getLocation().getId(), locationId)).collect(Collectors.toList());


        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (TaskEntity taskEntity : taskEntities) {
            TicketEntity reservations = taskEntity.getTicket();
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

        emailService.sendEmail("Ticket is ready!", "Your ticket is ready and waiting for you on location ", user);
        ticketService.changeTicketStatus(ticketEntity, TicketStatus.READY);
        TaskEntity task = taskRepository.findById(taskDTO.getId()).get();
        task.setStatus(TaskStatus.FINISHED);
        taskRepository.save(task);
        //taskRepository.delete(taskRepository.findById(taskDTO.getId()).get());

        return taskDTO;
    }


}
