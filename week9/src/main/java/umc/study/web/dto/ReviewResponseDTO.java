package umc.study.web.dto;

import lombok.Builder;
import lombok.Getter;
import umc.study.domain.Review;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponseDTO {

    private Long id;
    private String content;
    private Float score;
    private LocalDateTime createdAt;
    private String storeName;

    public static ReviewResponseDTO from(Review review) {
        return ReviewResponseDTO.builder()
                .id(review.getId())
                .content(review.getBody())
                .score(review.getScore())
                .createdAt(review.getCreatedAt())
                .storeName(review.getStore().getName())
                .build();
    }
}

