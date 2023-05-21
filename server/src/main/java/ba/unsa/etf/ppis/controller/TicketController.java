package ba.unsa.etf.ppis.controller;

import ba.unsa.etf.ppis.dto.AvailableTicketsDTO;
import ba.unsa.etf.ppis.dto.TicketDTO;
import ba.unsa.etf.ppis.service.AvailableTicketsService;
import ba.unsa.etf.ppis.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.EntityProjection;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    protected AvailableTicketsService availableTicketsService;

    @Autowired
    protected TicketService ticketService;

    @GetMapping("/getbytype/{id}")
    public ResponseEntity<AvailableTicketsDTO> getByType(@PathVariable("id") int id){
        return new ResponseEntity<>(availableTicketsService.getTicketsForEventByType(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<List<TicketDTO>> save(@RequestBody TicketDTO ticketDTO){
        return new ResponseEntity<>(ticketService.saveReservations(ticketDTO), HttpStatus.OK);
    }
}
