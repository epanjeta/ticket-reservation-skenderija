package ba.unsa.etf.ppis.repository;

import ba.unsa.etf.ppis.entity.AvailableTicketsEntity;
import ba.unsa.etf.ppis.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableTicketsRepository extends JpaRepository<AvailableTicketsEntity, Integer> {

    List<AvailableTicketsEntity> findAvailableTicketsEntitiesByEvent(EventEntity event);
}
