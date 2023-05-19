package ba.unsa.etf.ppis.controller;

import ba.unsa.etf.ppis.constants.ApiResponseMessages;
import ba.unsa.etf.ppis.dto.DateDTO;
import ba.unsa.etf.ppis.dto.EventDTO;
import ba.unsa.etf.ppis.service.EventService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired protected EventService eventService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.ALL_EVENTS_FOUND,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EventDTO.class))})}
    )
    @GetMapping("/all")
    public ResponseEntity<List<EventDTO>> getAll() {
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.EVENT_FOUND,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EventDTO.class))})}
    )
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable("id") int id) {
        return new ResponseEntity<>(eventService.getEvent(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EventDTO> saveEvent(@RequestBody EventDTO newEventDTO) {
        return new ResponseEntity<>(eventService.saveEvent(newEventDTO), HttpStatus.OK);
    }

    @PostMapping("/available")
    public ResponseEntity<DateDTO> checkIfAvailable(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") DateDTO date){
        return new ResponseEntity<>(eventService.checkIfAvailable(date), HttpStatus.OK);
    }
}
