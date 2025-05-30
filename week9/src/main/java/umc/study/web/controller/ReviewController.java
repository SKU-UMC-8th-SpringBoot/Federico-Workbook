package umc.study.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.study.service.ReviewService.ReviewService;
import umc.study.validation.annotation.ValidPage;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;


@RestController
@RequiredArgsConstructor
@RequestMapping
@Tag(name="Review" , description = "리뷰 관련 API")
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

    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "사용자가 작성한 리뷰를 페이징하여 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 페이지 요청")
    })
    @GetMapping("/me")
    public ResponseEntity<Page<ReviewResponseDTO>> getMyReviews(
            @RequestParam("memberId") Long memberId,
            @RequestParam("page") @Valid @ValidPage Integer page) {

        Pageable pageable = PageRequest.of(page - 1, 10);
        return ResponseEntity.ok(reviewService.getMyReviews(memberId, pageable));
    }
}
