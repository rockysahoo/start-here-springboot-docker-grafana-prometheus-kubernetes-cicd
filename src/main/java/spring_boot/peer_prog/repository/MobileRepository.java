package spring_boot.peer_prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_boot.peer_prog.entity.MobileEntity;

@Repository
public interface MobileRepository extends JpaRepository<MobileEntity, Long> {
}
