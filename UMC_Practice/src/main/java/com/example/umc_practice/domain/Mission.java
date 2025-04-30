package com.example.umc_practice.domain;

import jakarta.persistence.*;
import lombok.*;
import com.example.umc_practice.domain.common.BaseEntity;

import java.time.LocalDate;

@Entity
@Table(name = "mission")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 미션 ID

    @Column(nullable = false, length = 100)
    private String title; // 미션 이름

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description; // 미션 설명

    @Column(name = "point_reward", nullable = false)
    private Integer pointReward; // 포인트 보상

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region; // 대상 지역

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate; // 시작일

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate; // 종료일
}