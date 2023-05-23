package ba.unsa.etf.ppis.controller;

import ba.unsa.etf.ppis.dto.AvailableTicketsDTO;
import ba.unsa.etf.ppis.dto.TaskDTO;
import ba.unsa.etf.ppis.dto.TicketDTO;
import ba.unsa.etf.ppis.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/get/{id}")
    public ResponseEntity<List<TaskDTO>> getTaskByLocation(@PathVariable("id") Integer locationId){
        return new ResponseEntity<>(taskService.getTaskByLocationId(locationId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TaskDTO> save(@RequestBody TaskDTO taskDTO){
        return new ResponseEntity<>(taskService.createTask(taskDTO), HttpStatus.OK);
    }

    @DeleteMapping("/changeStatus")
    public ResponseEntity<TaskDTO> finishTask(@RequestBody TaskDTO taskDTO){
        return new ResponseEntity<>(taskService.finishTask(taskDTO), HttpStatus.OK);
    }
}
