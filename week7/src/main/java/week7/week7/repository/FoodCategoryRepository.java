package week7.week7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import week7.week7.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
