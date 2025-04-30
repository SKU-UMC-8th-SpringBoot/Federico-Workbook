package com.example.umc_practice.domain;

import com.example.umc_practice.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 리뷰 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 리뷰 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant; // 리뷰 대상 식당

    @Column(nullable = false)
    private String content; // 리뷰 내용

    @Column(nullable = false)
    private Integer score; // 별점 (1~5)

    @Column(name = "visited_date", nullable = false)
    private LocalDate visitedDate; // 방문일

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Picture> pictures; // 리뷰에 첨부된 사진들 .. 양방향 매핑이므로 리뷰가 삭제되면 사진들도 삭제됨.
}
