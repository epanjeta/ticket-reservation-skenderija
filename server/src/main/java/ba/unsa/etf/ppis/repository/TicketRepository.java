package ba.unsa.etf.ppis.repository;

import ba.unsa.etf.ppis.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
}
