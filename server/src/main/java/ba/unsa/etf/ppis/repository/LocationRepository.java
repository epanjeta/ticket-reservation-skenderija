package ba.unsa.etf.ppis.repository;

import ba.unsa.etf.ppis.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {
}
