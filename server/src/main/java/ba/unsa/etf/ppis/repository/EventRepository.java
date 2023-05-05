package ba.unsa.etf.ppis.repository;

import ba.unsa.etf.ppis.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Integer> {

    EventEntity findById(int id);
}
