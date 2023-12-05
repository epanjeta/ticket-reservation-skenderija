package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.constants.TicketStatus;
import ba.unsa.etf.ppis.dto.TicketDTO;
import ba.unsa.etf.ppis.entity.TicketEntity;

import java.util.List;

public interface TicketService {

    List<TicketDTO> saveReservations(TicketDTO reservations, String authorizationHeader);
    void changeTicketStatus(TicketEntity ticketEntity, TicketStatus ticketStatus);
    List<TicketDTO> getAllTicketsByUserId(Integer id, String authorizationHeader);
}
