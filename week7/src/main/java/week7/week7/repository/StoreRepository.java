package week7.week7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import week7.week7.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
