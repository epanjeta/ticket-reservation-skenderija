package ba.unsa.etf.ppis.repository;

import ba.unsa.etf.ppis.entity.JwtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtRepository extends JpaRepository<JwtEntity, Integer> {

    JwtEntity findJwtEntitiesByToken(String token);

}
