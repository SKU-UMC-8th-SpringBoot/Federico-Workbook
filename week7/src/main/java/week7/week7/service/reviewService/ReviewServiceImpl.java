package week7.week7.service.reviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import week7.week7.domain.Member;
import week7.week7.domain.Review;
import week7.week7.domain.Store;
import week7.week7.repository.MemberRepository;
import week7.week7.repository.ReviewRepository;
import week7.week7.repository.StoreRepository;
import week7.week7.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public void addReview(Long storeId, ReviewRequestDTO request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게가 없어요"));

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다.."));

        Review review = Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .member(member)
                .store(store)
                .build();

        reviewRepository.save(review);
    }
}
