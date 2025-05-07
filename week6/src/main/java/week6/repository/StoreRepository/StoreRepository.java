package week6.repository.StoreRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import week6.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}
