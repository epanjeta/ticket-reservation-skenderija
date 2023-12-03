package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.dto.TaskDTO;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface TaskService {

    TaskDTO createTask(TaskDTO taskDTO);
    List<TaskDTO> getTaskByLocationId(Integer locationId);
    TaskDTO finishTask(TaskDTO taskDTO);
}
