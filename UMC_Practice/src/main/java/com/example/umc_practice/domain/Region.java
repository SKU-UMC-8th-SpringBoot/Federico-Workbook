package com.example.umc_practice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "region")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 지역 ID

    @Column(nullable = false)
    private String name; // 지역 이름

    @Column(nullable = false)
    private Integer level; // 지역 단계 (예: 1 = 시도, 2 = 구군)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Region parent; // 상위 지역 (자기참조) PK와 매핑해서 찾음.

    @OneToMany(mappedBy = "parent")
    private List<Region> children; // 하위 지역 리스트
}