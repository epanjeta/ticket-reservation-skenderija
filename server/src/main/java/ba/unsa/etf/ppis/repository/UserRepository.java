package ba.unsa.etf.ppis.repository;

import ba.unsa.etf.ppis.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findUserEntityByEmailAndVerifiedIsTrue(String email);
}
