package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.dto.TicketDTO;
import ba.unsa.etf.ppis.dto.TicketTypeDTO;

import java.util.List;

public interface TicketService {

    List<TicketDTO> saveReservations(TicketDTO reservations);
}
