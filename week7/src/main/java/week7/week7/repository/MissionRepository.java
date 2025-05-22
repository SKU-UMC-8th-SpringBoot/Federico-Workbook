package week7.week7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import week7.week7.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
