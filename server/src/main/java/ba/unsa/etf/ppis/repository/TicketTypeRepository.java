package ba.unsa.etf.ppis.repository;

import ba.unsa.etf.ppis.entity.TicketTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketTypeEntity, Integer> {
}
