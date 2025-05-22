package week7.week7.service.reviewService;

import week7.week7.domain.Review;
import week7.week7.web.dto.ReviewRequestDTO;

public interface ReviewService {
    void addReview(Long storeId, ReviewRequestDTO request);
}
