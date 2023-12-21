package ba.unsa.etf.ppis.controller;

import ba.unsa.etf.ppis.constants.ApiResponseMessages;
import ba.unsa.etf.ppis.dto.AvailableTicketsDTO;
import ba.unsa.etf.ppis.dto.TaskDTO;
import ba.unsa.etf.ppis.dto.TicketDTO;
import ba.unsa.etf.ppis.dto.UserDTO;
import ba.unsa.etf.ppis.service.TaskService;
import ba.unsa.etf.ppis.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/get/{id}")
    public ResponseEntity<List<TaskDTO>> getTaskByLocation(@RequestHeader(name = "Authorization", required = false) String authorizationHeader, @PathVariable("id") Integer locationId){
        return new ResponseEntity<>(taskService.getTaskByLocationId(locationId, authorizationHeader), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TaskDTO> save(@RequestBody TaskDTO taskDTO){
        return new ResponseEntity<>(taskService.createTask(taskDTO), HttpStatus.OK);
    }

    @PutMapping("/changeStatus")
    public ResponseEntity<TaskDTO> finishTask(@RequestHeader(name = "Authorization", required = false) String authorizationHeader, @RequestBody TaskDTO taskDTO){
        return new ResponseEntity<>(taskService.finishTask(taskDTO, authorizationHeader), HttpStatus.OK);
    }
}
