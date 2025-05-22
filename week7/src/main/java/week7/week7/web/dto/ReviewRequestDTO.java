package week7.week7.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewRequestDTO {

    @NotBlank(message = "리뷰 내용은 필수입니다.")
    private String body;

    @NotNull(message = "리뷰 평점은 필수입니다.")
    private Float score;

    @NotNull(message = "회원정보가 없습니다.")
    private Long memberId;
}
