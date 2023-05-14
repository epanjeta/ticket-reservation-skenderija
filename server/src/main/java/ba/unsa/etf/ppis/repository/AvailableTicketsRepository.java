package ba.unsa.etf.ppis.repository;

import ba.unsa.etf.ppis.entity.AvailableTicketsEntity;
import ba.unsa.etf.ppis.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableTicketsRepository extends JpaRepository<AvailableTicketsEntity, Integer> {

    AvailableTicketsEntity findAvailableTicketsEntitiesByEvent(EventEntity event);
}
