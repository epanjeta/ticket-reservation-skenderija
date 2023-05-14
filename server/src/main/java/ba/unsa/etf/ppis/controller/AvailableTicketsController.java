package ba.unsa.etf.ppis.controller;


import ba.unsa.etf.ppis.dto.AvailableTicketsDTO;
import ba.unsa.etf.ppis.dto.EventDTO;
import ba.unsa.etf.ppis.dto.NewEventDTO;
import ba.unsa.etf.ppis.service.AvailableTicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/availabletickets")
public class AvailableTicketsController {

    @Autowired
    protected AvailableTicketsService availableTicketsService;

    @PostMapping("/create")
    public ResponseEntity<AvailableTicketsDTO> save(@RequestBody AvailableTicketsDTO availableTicketsDTO) {
        return new ResponseEntity<>(availableTicketsService.saveAvailableTicketsForEvent(availableTicketsDTO), HttpStatus.OK);
    }
}
