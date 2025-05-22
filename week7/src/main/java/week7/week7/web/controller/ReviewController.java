package week7.week7.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week7.week7.service.reviewService.ReviewService;
import week7.week7.web.dto.ReviewRequestDTO;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "가게에 리뷰 추가", description = "storeId에 해당하는 가게에 리뷰를 작성합니다.")
    @PostMapping("/{storeId}/reviews")
    public ResponseEntity<String> addReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewRequestDTO request
    ) {
        reviewService.addReview(storeId, request);
        return ResponseEntity.ok("리뷰 작성 완료");
    }
}
