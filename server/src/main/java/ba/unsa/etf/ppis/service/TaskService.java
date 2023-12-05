package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO createTask(TaskDTO taskDTO);
    List<TaskDTO> getTaskByLocationId(Integer locationId, String authorizationHeader);
    TaskDTO finishTask(TaskDTO taskDTO, String authorizationHeader);
}
