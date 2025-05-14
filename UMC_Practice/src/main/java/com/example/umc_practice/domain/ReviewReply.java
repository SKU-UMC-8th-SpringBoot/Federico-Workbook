package com.example.umc_practice.domain;

import com.example.umc_practice.domain.common.BaseEntity;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "review_reply")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ReviewReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 답글 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review; // 어떤 리뷰에 대한 답글인지

    @Column(nullable = false)
    private String content; // 답글 내용
}

