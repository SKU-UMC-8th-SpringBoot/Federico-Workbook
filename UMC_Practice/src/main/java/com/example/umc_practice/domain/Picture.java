package com.example.umc_practice.domain;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "picture")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 사진 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review; // 어떤 리뷰에 첨부된 사진인지

    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl; // 이미지 URL
}

