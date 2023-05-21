package ba.unsa.etf.ppis.repository;

import ba.unsa.etf.ppis.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
}
