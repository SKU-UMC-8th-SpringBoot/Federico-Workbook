package com.example.umc_practice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 식당 ID

    @Column(nullable = false)
    private String name; // 식당 이름

    @Column(nullable = false)
    private String address; // 주소

    @Column(nullable = false)
    private Double latitude; // 위도

    @Column(nullable = false)
    private Double longitude; // 경도

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region; // 지역

    @Column(nullable = false, length = 50)
    private String category; // 음식 카테고리

    @Column(length = 20)
    private String phoneNumber; // 전화번호

    private LocalTime openTime; // 영업 시작 시간

    private LocalTime closeTime; // 영업 종료 시간

    private LocalTime breakStart; // 브레이크 시작

    private LocalTime breakEnd; // 브레이크 종료

    @Column(length = 20)
    private String closedDay; // 정기 휴무일 (예: "월요일")

    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodRestaurant> foodRestaurants = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissionRestaurant> missionRestaurants = new ArrayList<>();
}
